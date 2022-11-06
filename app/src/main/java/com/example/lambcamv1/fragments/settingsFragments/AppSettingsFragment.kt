package com.example.lambcamv1.fragments.settingsFragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.lambcamv1.R

class AppSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}