package com.example.lambcamv1.fragments.lambListFragments

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
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lambcamv1.R
import com.example.lambcamv1.fragments.HomeFragment
import com.example.lambcamv1.fragments.lambListFragments.LambListFragment
import com.example.lambcamv1.models.Lamb
import com.example.lambcamv1.viewModels.LambViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_lamb.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class AddLambFragment : Fragment() {

    private lateinit var mLambViewModel: LambViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_add_lamb, container, false)

        // Get Lamb view model
        mLambViewModel = ViewModelProvider(this).get(LambViewModel::class.java)

        // Pass correct fragment to lamb list fragment container
        val lambListFragment = LambListFragment()
        loadLambListFragment(lambListFragment)

        // Find add and back buttons
        val backButton = v.findViewById<FloatingActionButton>(R.id.backFAB)
        val addButton = v.findViewById<Button>(R.id.addLambButton)

        // Set back button to return to home fragment
        backButton.setOnClickListener {
            val homePageFragment = HomeFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, homePageFragment)
            transaction.commit()
        }

        // Close keyboard and call insertDataToDatabase function
        addButton.setOnClickListener{
            it.hideKeyboard()
            insertDataToDatabase()
        }

        return v
    }

    // Load lamb list RecyclerView in frame layout
    private fun loadLambListFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.lambFragmentContainer, fragment)
        transaction.commit()
    }

    private fun insertDataToDatabase() {
        // Get current values entered
        val eweTagNo = eweTagNoEditText.text.toString()
        val tupTagNo = tupTagNoEditText.text.toString()
        val lambMarking = lambMarkingEditText.text.toString()
        val lambSex = lambSexSpinner.selectedItem.toString()
        val DOB = dateEditText.text.toString()
        val lambSiblings = lambSiblingsSpinner.selectedItem.toString()
        val comments = commentsEditText.text.toString()

        // Check inputs
        if(inputCheck(eweTagNo, tupTagNo, lambMarking, DOB)){
            // Set values of new tup item
            val lamb = Lamb(0, eweTagNo, tupTagNo, lambMarking, lambSex, DOB, lambSiblings, comments)
            // Add tup to view model
            mLambViewModel.addLamb(lamb)
            // Clear current text
            clearText()
            // Success toast
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
        }else{
            // Warning toast
            Toast.makeText(requireContext(), "Please fill all fields.", Toast.LENGTH_SHORT).show()
        }
    }

    // Input check to make sure all necessary fields complete
    private fun inputCheck(eweTagNo: String,  tupTagNo: String, lambMarking: String, DOB: String): Boolean{
        return !(TextUtils.isEmpty(eweTagNo) || TextUtils.isEmpty(tupTagNo) || TextUtils.isEmpty(lambMarking) || TextUtils.isEmpty(DOB))
    }

    // Close keyboard after adding lamb
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    // Clear text after adding lamb
    private fun clearText() {
        eweTagNoEditText.text.clear()
        tupTagNoEditText.text.clear()
        dateEditText.text.clear()
        lambMarkingEditText.text.clear()
        commentsEditText.text.clear()
    }


}
