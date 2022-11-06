package com.example.lambcamv1.fragments.tupListFragments

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
import com.example.lambcamv1.models.Tup
import com.example.lambcamv1.viewModels.TupViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_tup.*

class AddTupFragment : Fragment() {

    private lateinit var mTupViewModel: TupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_add_tup, container, false)

        // Get Tup view model
        mTupViewModel = ViewModelProvider(this).get(TupViewModel::class.java)

        // Find add and back buttons
        val backButton = v.findViewById<FloatingActionButton>(R.id.backFAB)
        val addButton = v.findViewById<Button>(R.id.addTupButton)

        // Set back button to return to home fragment
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_addTupFragment_to_tupListFragment)
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
        val tupTagNo = tupTagNoEditText.text.toString()
        val tupBreed = tupBreedEditText.text.toString()
        val tupAge = tupAgeEditText.text

        // Check inputs
        if(inputCheck(tupTagNo, tupBreed, tupAge)){
            // Set values of new tup item
            val tup = Tup(0, tupTagNo, tupBreed, Integer.parseInt(tupAge.toString()))
            // Add tup to view model
            mTupViewModel.addTup(tup)
            // Success toast
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            // Navigate back to list
            findNavController().navigate(R.id.action_addTupFragment_to_tupListFragment)
        }else{
            // Warning toast
            Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    // Input check to make sure all fields complete
    private fun inputCheck(tupTagNo: String, tupBreed: String, tupAge: Editable): Boolean{
        return !(TextUtils.isEmpty(tupTagNo) || TextUtils.isEmpty(tupBreed) || tupAge.isEmpty())
    }

    // Close keyboard after adding Tup
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}