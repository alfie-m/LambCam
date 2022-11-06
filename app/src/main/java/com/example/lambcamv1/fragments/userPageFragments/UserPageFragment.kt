package com.example.lambcamv1.fragments.userPageFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.lambcamv1.R
import com.example.lambcamv1.fragments.HomeFragment
import com.example.lambcamv1.viewModels.EweViewModel
import com.example.lambcamv1.viewModels.LambViewModel
import com.example.lambcamv1.viewModels.TupViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_user.*

class UserPageFragment : Fragment() {

    // Initiate all necessary view models
    private lateinit var mEweViewModel: EweViewModel
    private lateinit var mTupViewModel: TupViewModel
    private lateinit var mLambViewModel: LambViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user, container, false)

        // Get corresponding view models
        mEweViewModel = ViewModelProvider(this).get(EweViewModel::class.java)
        mTupViewModel = ViewModelProvider(this).get(TupViewModel::class.java)
        mLambViewModel = ViewModelProvider(this).get(LambViewModel::class.java)

        // Populate statistics textViews with corresponding values from databases

        mEweViewModel.countEwes.observe(this, Observer { countEwes ->
            eweNumberBox.text = "$countEwes"
        })

        mTupViewModel.countTups.observe(this, Observer { countTups ->
            tupNumberBox.text = "$countTups"
        })

        mLambViewModel.countLambs.observe(this, Observer { countLambs ->
            lambNumberBox.text = "$countLambs"
        })

        mLambViewModel.countSingles.observe(this, Observer { countSingles ->
            singlesBox.text = "$countSingles"
        })

        mLambViewModel.countTwins.observe(this, Observer { countTwins ->
            twinsBox.text = "$countTwins"
        })

        mLambViewModel.countTriplets.observe(this, Observer { countTriplets ->
            tripletsBox.text = "$countTriplets"
        })

        mLambViewModel.countQuadruplets.observe(this, Observer { countQuadruplets ->
            quadrupletsBox.text = "$countQuadruplets"
        })

        mLambViewModel.countEweLambs.observe(this, Observer { countEweLambs ->
            eweLambsBox.text = "$countEweLambs"
        })

        mLambViewModel.countTupLambs.observe(this, Observer { countTupLambs ->
            tupLambsBox.text = "$countTupLambs"
        })

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

    // Call loadSettings function
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadSettings()
    }

    // Populate address textViews using shared preferences set in settings
    private fun loadSettings(){
        val sp = PreferenceManager.getDefaultSharedPreferences(context)

        val farmName = sp.getString("farm_name", "")
        val farmTown = sp.getString("farm_town", "")
        val farmCounty = sp.getString("farm_county", "")
        val farmPostcode = sp.getString("farm_postcode", "")

        farmNameBox.text = farmName
        countyBox.text = farmCounty
        townBox.text = farmTown
        postcodeBox.text = farmPostcode

    }

}