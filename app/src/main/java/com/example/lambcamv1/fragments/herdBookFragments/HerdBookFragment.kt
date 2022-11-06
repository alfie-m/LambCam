package com.example.lambcamv1.fragments.herdBookFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.lambcamv1.R
import com.example.lambcamv1.adapters.ViewPagerAdapter
import com.example.lambcamv1.fragments.HomeFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_herd_book.*

class HerdBookFragment : Fragment() {

    private val myContext = FragmentActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_herd_book, container, false)

        // Find back button manually
        val backButton = v.findViewById<FloatingActionButton>(R.id.backFAB)
        // Set back button to return to home fragment
        backButton.setOnClickListener {
            val homePageFragment = HomeFragment()
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, homePageFragment)
            transaction.commit()
        }

        return v
    }

    // Call setUpTabBar function
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabBar()
    }

    // Set up tabs using ViewPagerAdapter
    private fun setUpTabBar() {
        val adapter = ViewPagerAdapter(this, herdBookTabs.tabCount)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int){
                herdBookTabs.selectTab((herdBookTabs.getTabAt(position)))
            }
        })

        herdBookTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}

        })
    }

}