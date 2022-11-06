package com.example.lambcamv1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lambcamv1.R
import com.example.lambcamv1.models.Tup
import com.example.lambcamv1.fragments.tupListFragments.TupListFragmentDirections
import kotlinx.android.synthetic.main.tup_custom_row.view.*

class TupListAdapter: RecyclerView.Adapter<TupListAdapter.MyTupViewHolder>() {

    // Create list of Tup objects
    private var tupList = emptyList<Tup>()

    // Initiate view holder
    class MyTupViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TupListAdapter.MyTupViewHolder {
        return MyTupViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tup_custom_row, parent, false))
    }

    // Fill rows with tup data
    override fun onBindViewHolder(tupHolder: TupListAdapter.MyTupViewHolder, position: Int) {
        val currentTup = tupList[position]
        tupHolder.itemView.tupTagNoTextView.text = currentTup.tagNo.toString()
        tupHolder.itemView.tupBreedTextView.text = currentTup.breed.toString()
        tupHolder.itemView.tupAgeTextView.text = currentTup.age.toString()

        // Navigation controller to view update fragment when row is clicked
        tupHolder.itemView.tupRowLayout.setOnClickListener {
            val action = TupListFragmentDirections.actionTupListFragmentToUpdateTupFragment(currentTup)
            tupHolder.itemView.findNavController().navigate(action)
        }
    }

    // Count length of list of Tup objects
    override fun getItemCount(): Int {
        return tupList.size
    }

    fun setData(tup: List<Tup>){
        this.tupList = tup
        notifyDataSetChanged()
    }

}