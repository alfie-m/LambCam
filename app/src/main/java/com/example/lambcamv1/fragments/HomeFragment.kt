package com.example.lambcamv1.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.preference.PreferenceManager
import com.example.lambcamv1.R
import com.example.lambcamv1.fragments.lambListFragments.AddLambFragment
import com.example.lambcamv1.fragments.herdBookFragments.HerdBookFragment
import com.example.lambcamv1.fragments.lambListFragments.LambListFragment
import com.example.lambcamv1.fragments.lambListFragments.LambListHostFragment
import com.example.lambcamv1.fragments.userPageFragments.UserPageFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        // Find buttons manually to avoid null pointer exceptions
        val bt1 = v.findViewById<Button>(R.id.userPageButton)
        val bt2 = v.findViewById<Button>(R.id.herdBookButton)
        val bt3 = v.findViewById<Button>(R.id.lambListButton)
        val bt4 = v.findViewById<Button>(R.id.addButton)

        // Set button listeners to navigate to corresponding fragment
        bt1.setOnClickListener{
            val userPageFragment = UserPageFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, userPageFragment)
            transaction.commit()
        }

        bt2.setOnClickListener{
            val herdBookFragment = HerdBookFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, herdBookFragment)
            transaction.commit()
        }

        bt3.setOnClickListener{
            val lambListFragment = LambListHostFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, lambListFragment)
            transaction.commit()
        }

        bt4.setOnClickListener{
            val addFragment = AddLambFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, addFragment)
            transaction.commit()
        }

        // Pass correct fragment to lamb list fragment container
        val lambListFragment = LambListFragment()
        loadLambListFragment(lambListFragment)

        return v
    }

    // Set farm name on home fragment using shared preferences set in settings
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val farmName = sp.getString("farm_name", "")
        val farmTown = sp.getString("farm_town", "")
        addressBox.text = "$farmName, $farmTown"
    }

    // Load lamb list RecyclerView in frame layout
    private fun loadLambListFragment(fragment: Fragment){
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.lambFragmentContainer, fragment)
        transaction.commit()
    }
}