package com.example.lambcamv1.fragments.eweListFragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lambcamv1.R
import com.example.lambcamv1.models.Ewe
import com.example.lambcamv1.viewModels.EweViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_ewe.*

class AddEweFragment : Fragment() {

    private lateinit var mEweViewModel: EweViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_add_ewe, container, false)

        // Get Ewe view model
        mEweViewModel = ViewModelProvider(this).get(EweViewModel::class.java)

        // Find add and back buttons
        val backButton = v.findViewById<FloatingActionButton>(R.id.backFAB)
        val addButton = v.findViewById<Button>(R.id.addEweButton)

        // Set back button to return to home fragment
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_addEweFragment_to_eweListFragment2)
        }

        // Close keyboard and call insertDataToDatabase function
        addButton.setOnClickListener{
            it.hideKeyboard()
            insertDataToDatabase()
        }

        return v
    }

    private fun insertDataToDatabase() {
        // Get current values entered
        val eweTagNo = eweTagNoEditText.text.toString()
        val eweBreed = eweBreedEditText.text.toString()
        val eweAge = eweAgeEditText.text

        // Check inputs
        if(inputCheck(eweTagNo, eweBreed, eweAge)){
            // Set values of new ewe item
            val ewe = Ewe(0, eweTagNo, eweBreed, Integer.parseInt(eweAge.toString()))
            // Add ewe to view model
            mEweViewModel.addEwe(ewe)
            // Success toast
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            // Navigate back to list
            findNavController().navigate(R.id.action_addEweFragment_to_eweListFragment2)
        }else{
            // Warning toast
            Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    // Input check to make sure all fields complete
    private fun inputCheck(eweTagNo: String, eweBreed: String, eweAge: Editable): Boolean{
        return !(TextUtils.isEmpty(eweTagNo) || TextUtils.isEmpty(eweBreed) || eweAge.isEmpty())
    }

    // Close keyboard after adding Ewe
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}