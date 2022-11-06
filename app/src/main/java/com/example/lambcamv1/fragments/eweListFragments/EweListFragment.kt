package com.example.lambcamv1.fragments.eweListFragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lambcamv1.R
import com.example.lambcamv1.adapters.EweListAdapter
import com.example.lambcamv1.viewModels.EweViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_ewe_list.view.*

class EweListFragment : Fragment() {

    private lateinit var mEweViewModel: EweViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_ewe_list, container, false)


        // Recyclerview
        val eweAdapter = EweListAdapter()
        val eweRecyclerView = v.eweRecyclerView
        eweRecyclerView.adapter = eweAdapter
        eweRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // EweViewModel
        mEweViewModel = ViewModelProvider(this).get(EweViewModel::class.java)
        mEweViewModel.readAllData.observe(viewLifecycleOwner, Observer { ewe ->
            eweAdapter.setData(ewe)
        })

        // Find add and delete buttons
        val addButton = v.findViewById<FloatingActionButton>(R.id.addFAB)
        val deleteButton = v.findViewById<FloatingActionButton>(R.id.deleteFAB)

        // Navigate to add page
        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_eweListFragment2_to_addEweFragment)
        }

        // Call deleteAllItems function
        deleteButton.setOnClickListener {
            deleteAllItems()
        }

        return v
    }

    // Delete all Ewe items
    private fun deleteAllItems(){
        // Use dialog box to confirm
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            // Call delete function
            mEweViewModel.deleteAllEwes()
            Toast.makeText(requireContext(), "All items deleted!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){ _, _ ->

        }
        builder.setTitle("Delete all?")
        builder.setMessage("Are you sure you want to delete items in this list?")
        builder.create().show()
    }

}