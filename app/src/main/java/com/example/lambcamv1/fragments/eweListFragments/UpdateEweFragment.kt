package com.example.lambcamv1.fragments.eweListFragments

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
import com.example.lambcamv1.models.Ewe
import com.example.lambcamv1.viewModels.EweViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_update_ewe.*
import kotlinx.android.synthetic.main.fragment_update_ewe.view.*

class UpdateEweFragment : Fragment() {

    private val args by navArgs<com.example.lambcamv1.fragments.eweListFragments.UpdateEweFragmentArgs>()

    private lateinit var mEweViewModel: EweViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_update_ewe, container, false)

        // Get Ewe view model
        mEweViewModel = ViewModelProvider(this).get(EweViewModel::class.java)

        // Set edit texts to currently selected items values
        v.updateEweTagNoEditText.setText(args.currentEwe.tagNo)
        v.updateEweBreedEditText.setText(args.currentEwe.breed)
        v.updateEweAgeEditText.setText(args.currentEwe.age.toString())

        // Find buttons
        val updateButton = v.findViewById<Button>(R.id.updateEweButton)
        val deleteButton = v.findViewById<Button>(R.id.deleteEweButton)
        val backButton = v.findViewById<FloatingActionButton>(R.id.backFAB)

        // Set back button to return to home fragment
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_updateEweFragment_to_eweListFragment2)
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
        val eweTagNo = updateEweTagNoEditText.text.toString()
        val eweBreed = updateEweBreedEditText.text.toString()
        val eweAge = Integer.parseInt(updateEweAgeEditText.text.toString())

        // Check inputs
        if(inputCheck(eweTagNo, eweBreed, updateEweAgeEditText.text)){
            // Create Ewe Object
            val updatedEwe = Ewe(args.currentEwe.id, eweTagNo, eweBreed, eweAge)
            // Update current ewe
            mEweViewModel.updateEwe(updatedEwe)
            // Success toast
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            // Navigate back to list
            findNavController().navigate(R.id.action_updateEweFragment_to_eweListFragment2)
        }else{
            // Warning toast
            Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    // Input check to make sure all fields complete
    private fun inputCheck(eweTagNo: String, eweBreed: String, eweAge: Editable): Boolean{
        return !(TextUtils.isEmpty(eweTagNo) || TextUtils.isEmpty(eweBreed) || eweAge.isEmpty())
    }

    // Delete current item
    private fun deleteItem(){
        // Use dialog box to confirm
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            // Call delete function for current item
            mEweViewModel.deleteEwe(args.currentEwe)
            Toast.makeText(requireContext(), "Successfully deleted item!", Toast.LENGTH_SHORT).show()
            // Navigate back to list
            findNavController().navigate(R.id.action_updateEweFragment_to_eweListFragment2)
        }
        builder.setNegativeButton("No"){ _, _ ->

        }
        builder.setTitle("Delete ${args.currentEwe.tagNo}?")
        builder.setMessage("Are you sure you want to delete ${args.currentEwe.tagNo}?")
        builder.create().show()
    }

    // Close keyboard after updating Ewe
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}