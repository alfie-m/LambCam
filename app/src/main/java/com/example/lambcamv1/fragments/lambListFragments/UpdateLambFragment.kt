package com.example.lambcamv1.fragments.lambListFragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lambcamv1.R
import com.example.lambcamv1.models.Lamb
import com.example.lambcamv1.viewModels.LambViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_lamb.*
import kotlinx.android.synthetic.main.fragment_update_lamb.*
import kotlinx.android.synthetic.main.fragment_update_lamb.commentsEditText
import kotlinx.android.synthetic.main.fragment_update_lamb.dateEditText
import kotlinx.android.synthetic.main.fragment_update_lamb.eweTagNoEditText
import kotlinx.android.synthetic.main.fragment_update_lamb.lambMarkingEditText
import kotlinx.android.synthetic.main.fragment_update_lamb.lambSexSpinner
import kotlinx.android.synthetic.main.fragment_update_lamb.lambSiblingsSpinner
import kotlinx.android.synthetic.main.fragment_update_lamb.tupTagNoEditText
import kotlinx.android.synthetic.main.fragment_update_lamb.view.*

class UpdateLambFragment : Fragment() {

    private val args by navArgs<com.example.lambcamv1.fragments.lambListFragments.UpdateLambFragmentArgs>()

    private lateinit var mLambViewModel: LambViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_update_lamb, container, false)

        // Get Lamb view model
        mLambViewModel = ViewModelProvider(this).get(LambViewModel::class.java)

        // Set edit texts to currently selected items values
        v.eweTagNoEditText.setText(args.currentLamb.eweTagNo)
        v.tupTagNoEditText.setText(args.currentLamb.tupTagNo)
        v.lambMarkingEditText.setText(args.currentLamb.marking)
        v.lambSexSpinner.setSelection(0)
        v.dateEditText.setText(args.currentLamb.DOB)
        v.lambSiblingsSpinner.setSelection(0)
        v.commentsEditText.setText(args.currentLamb.comments)

        // Find buttons
        val updateButton = v.findViewById<Button>(R.id.updateLambButton)
        val deleteButton = v.findViewById<Button>(R.id.deleteLambButton)
        val backButton = v.findViewById<FloatingActionButton>(R.id.backFAB)

        // Set back button to return to home fragment
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_updateLambFragment_to_lambListFragment)
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
        val eweTagNo = eweTagNoEditText.text.toString()
        val tupTagNo = tupTagNoEditText.text.toString()
        val lambMarking = lambMarkingEditText.text.toString()
        val lambSex = lambSexSpinner.selectedItem.toString()
        val DOB = dateEditText.text.toString()
        val lambSiblings = lambSiblingsSpinner.selectedItem.toString()
        val comments = commentsEditText.text.toString()

        // Check inputs
        if(inputCheck(eweTagNo, tupTagNo, lambMarking, DOB)){
            // Create Lamb Object
            val updatedLamb = Lamb(args.currentLamb.id, eweTagNo, tupTagNo, lambMarking, lambSex, DOB, lambSiblings, comments)
            // Update current lamb
            mLambViewModel.updateLamb(updatedLamb)
            // Success toast
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
            // Navigate back to list
            findNavController().navigate(R.id.action_updateLambFragment_to_lambListFragment)

        }else{
            // Warning toast
            Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    // Input check to make sure all necessary fields complete
    private fun inputCheck(eweTagNo: String,  tupTagNo: String, lambMarking: String, DOB: String): Boolean{
        return !(TextUtils.isEmpty(eweTagNo) || TextUtils.isEmpty(tupTagNo) || TextUtils.isEmpty(lambMarking) || TextUtils.isEmpty(DOB))
    }

    // Delete current item
    private fun deleteItem(){
        // Use dialog box to confirm
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            // Call delete function for current item
            mLambViewModel.deleteLamb(args.currentLamb)
            Toast.makeText(requireContext(), "Successfully deleted item!", Toast.LENGTH_SHORT).show()
            // Navigate back to list
            findNavController().navigate(R.id.action_updateLambFragment_to_lambListFragment)
        }
        builder.setNegativeButton("No"){ _, _ ->

        }
        builder.setTitle("Delete ${args.currentLamb.marking}?")
        builder.setMessage("Are you sure you want to delete ${args.currentLamb.marking}?")
        builder.create().show()
    }

    // Close keyboard after updating lamb
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}