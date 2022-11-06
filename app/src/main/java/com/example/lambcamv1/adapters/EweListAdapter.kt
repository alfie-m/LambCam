package com.example.lambcamv1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lambcamv1.R
import com.example.lambcamv1.models.Ewe
import com.example.lambcamv1.fragments.eweListFragments.EweListFragmentDirections
import kotlinx.android.synthetic.main.ewe_custom_row.view.*

class EweListAdapter: RecyclerView.Adapter<EweListAdapter.MyEweViewHolder>() {

    // Create list of Ewe objects
    private var eweList = emptyList<Ewe>()

    // Initiate view holder
    class MyEweViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EweListAdapter.MyEweViewHolder {
        return MyEweViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.ewe_custom_row, parent, false))
    }

    // Fill rows with ewe data
    override fun onBindViewHolder(eweHolder: EweListAdapter.MyEweViewHolder, position: Int) {
        val currentEwe = eweList[position]
        eweHolder.itemView.eweTagNoTextView.text = currentEwe.tagNo.toString()
        eweHolder.itemView.eweBreedTextView.text = currentEwe.breed.toString()
        eweHolder.itemView.eweAgeTextView.text = currentEwe.age.toString()

        // Navigation controller to view update fragment when row is clicked
        eweHolder.itemView.eweRowLayout.setOnClickListener {
            val action = EweListFragmentDirections.actionEweListFragment2ToUpdateEweFragment(currentEwe)
            eweHolder.itemView.findNavController().navigate(action)
        }
    }

    // Count length of list of Ewe objects
    override fun getItemCount(): Int {
        return eweList.size
    }

    fun setData(ewe: List<Ewe>){
        this.eweList = ewe
        notifyDataSetChanged()
    }

}