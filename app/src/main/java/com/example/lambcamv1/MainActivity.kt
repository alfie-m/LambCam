package com.example.lambcamv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lambcamv1.fragments.lambListFragments.AddLambFragment
import com.example.lambcamv1.fragments.HomeFragment
import com.example.lambcamv1.fragments.settingsFragments.AppSettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Fragments used when bottom navigation bar implemented
    private val homeFragment = HomeFragment()
    private val addLambFragment = AddLambFragment()
    private val settingsFragment = AppSettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Set initial fragment to homeFragment
        replaceFragment(homeFragment)

        // Listener to send correct fragment to replaceFragment function
        bottom_navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_add -> replaceFragment(addLambFragment)
                R.id.ic_settings-> replaceFragment(settingsFragment)
            }
            true
        }
    }

    // Function to control bottom navigation bar fragment change
    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}