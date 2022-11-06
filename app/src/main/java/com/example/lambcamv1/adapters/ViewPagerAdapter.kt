package com.example.lambcamv1.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lambcamv1.fragments.eweListFragments.EweListHostFragment
import com.example.lambcamv1.fragments.herdBookFragments.HerdBookFragment
import com.example.lambcamv1.fragments.tupListFragments.TupListHostFragment

class ViewPagerAdapter(activity: HerdBookFragment, private val tabCount: Int) : FragmentStateAdapter(activity) {

    // ViewPagerAdapter to control ewe/tup tabs in the herdbook fragment

    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position)
        {
            0 -> EweListHostFragment()
            1 -> TupListHostFragment()
            // set initial tab to ewe list
            else -> EweListHostFragment()
        }
    }

}