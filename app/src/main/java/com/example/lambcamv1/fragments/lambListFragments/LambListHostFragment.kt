package com.example.lambcamv1.fragments.lambListFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.lambcamv1.R
import com.example.lambcamv1.fragments.HomeFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class LambListHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_lamb_list_host, container, false)

        // Find back button
        val backButton = v.findViewById<FloatingActionButton>(R.id.backFAB)

        // Navigate back to home fragment
        backButton.setOnClickListener {
            val homePageFragment = HomeFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, homePageFragment)
            transaction.commit()
        }

        return v
    }

}