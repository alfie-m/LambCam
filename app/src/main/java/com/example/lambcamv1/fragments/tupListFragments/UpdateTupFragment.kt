package com.example.lambcamv1.fragments.tupListFragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lambcamv1.R
import com.example.lambcamv1.models.Tup
import com.example.lambcamv1.viewModels.TupViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_update_tup.*
import kotlinx.android.synthetic.main.fragment_update_tup.view.*

class UpdateTupFragment : Fragment() {

    private val args by navArgs<com.example.lambcamv1.fragments.tupListFragments.UpdateTupFragmentArgs>()

    private lateinit var mTupViewModel: TupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_update_tup, container, false)

        // Get Tup view model
        mTupViewModel = ViewModelProvider(this).get(TupViewModel::class.java)

        // Set edit texts to currently selected items values
        v.updateTupTagNoEditText.setText(args.currentTup.tagNo)
        v.updateTupBreedEditText.setText(args.currentTup.breed)
        v.updateTupAgeEditText.setText(args.currentTup.age.toString())

        // Find buttons
        val updateButton = v.findViewById<Button>(R.id.updateTupButton)
        val deleteButton = v.findViewById<Button>(R.id.deleteTupButton)
        val backButton = v.findViewById<FloatingActionButton>(R.id.backFAB)

        // Set back button to return to home fragment
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_updateTupFragment_to_tupListFragment)
        }

        // Close keyboard and call updateItem function
        updateButton.setOnClickListener {
            it.hideKeyboard()
            updateItem()
        }

        // Call deleteItem function
        deleteButton.setOnClickListener {
            deleteItem()
        }

        return v
    }

    private fun updateItem(){
        // Get current (potentially changed) edit text values
        val tupTagNo = updateTupTagNoEditText.text.toString()
        val tupBreed = updateTupBreedEditText.text.toString()
        val tupAge = Integer.parseInt(updateTupAgeEditText.text.toString())

        // Check inputs
        if(inputCheck(tupTagNo, tupBreed, updateTupAgeEditText.text)){
            // Create Tup Object
            val updatedTup = Tup(args.currentTup.id, tupTagNo, tupBreed, tupAge)
            // Update current Tup
            mTupViewModel.updateTup(updatedTup)
            // Success toast
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            // Navigate back to list
            findNavController().navigate(R.id.action_updateTupFragment_to_tupListFragment)
        }else{
            // Warning toast
            Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    // Input check to make sure all fields complete
    private fun inputCheck(tupTagNo: String, tupBreed: String, tupAge: Editable): Boolean{
        return !(TextUtils.isEmpty(tupTagNo) || TextUtils.isEmpty(tupBreed) || tupAge.isEmpty())
    }

    // Delete current item
    private fun deleteItem(){
        // Use dialog box to confirm
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            // Call delete function for current item
            mTupViewModel.deleteTup(args.currentTup)
            Toast.makeText(requireContext(), "Successfully deleted item!", Toast.LENGTH_SHORT).show()
            // Navigate back to list
            findNavController().navigate(R.id.action_updateTupFragment_to_tupListFragment)
        }
        builder.setNegativeButton("No"){ _, _ ->

        }
        builder.setTitle("Delete ${args.currentTup.tagNo}?")
        builder.setMessage("Are you sure you want to delete ${args.currentTup.tagNo}?")
        builder.create().show()
    }

    // Close keyboard after updating Ewe
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}