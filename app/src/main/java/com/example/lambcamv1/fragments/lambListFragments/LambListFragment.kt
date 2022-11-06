package com.example.lambcamv1.fragments.lambListFragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lambcamv1.R
import com.example.lambcamv1.adapters.LambListAdapter
import com.example.lambcamv1.fragments.HomeFragment
import com.example.lambcamv1.viewModels.LambViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_lamb_list.view.*

class LambListFragment : Fragment() {

    private lateinit var mLambViewModel: LambViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_lamb_list, container, false)

        // Recyclerview
        val lambAdapter = LambListAdapter()
        val lambRecyclerView = v.lambRecyclerView
        lambRecyclerView.adapter = lambAdapter
        lambRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // LambViewModel
        mLambViewModel = ViewModelProvider(this).get(LambViewModel::class.java)
        mLambViewModel.readAllData.observe(viewLifecycleOwner, Observer { lamb ->
            lambAdapter.setData(lamb)
        })

        return v
    }

}