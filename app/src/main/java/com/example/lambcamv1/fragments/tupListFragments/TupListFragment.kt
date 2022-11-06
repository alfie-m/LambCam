package com.example.lambcamv1.fragments.tupListFragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lambcamv1.R
import com.example.lambcamv1.adapters.TupListAdapter
import com.example.lambcamv1.viewModels.TupViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_tup_list.view.*

class TupListFragment : Fragment() {

    private lateinit var mTupViewModel: TupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_tup_list, container, false)

        // Recyclerview
        val tupAdapter = TupListAdapter()
        val tupRecyclerView = v.tupRecyclerView
        tupRecyclerView.adapter = tupAdapter
        tupRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        mTupViewModel = ViewModelProvider(this).get(TupViewModel::class.java)
        mTupViewModel.readAllData.observe(viewLifecycleOwner, Observer { tup ->
            tupAdapter.setData(tup)
        })

        // Find add and delete buttons
        val addButton = v.findViewById<FloatingActionButton>(R.id.addFAB)
        val deleteButton = v.findViewById<FloatingActionButton>(R.id.deleteFAB)

        // Navigate to add page
        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_tupListFragment_to_addTupFragment)
        }

        // Call deleteAllItems function
        deleteButton.setOnClickListener {
            deleteAllItems()
        }

        return v
    }

    // Delete all Tup items
    private fun deleteAllItems(){
        // Use dialog box to confirm
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            // Call delete function
            mTupViewModel.deleteAllTups()
            Toast.makeText(requireContext(), "All items deleted!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){ _, _ ->

        }
        builder.setTitle("Delete all?")
        builder.setMessage("Are you sure you want to delete items in this list?")
        builder.create().show()
    }

}