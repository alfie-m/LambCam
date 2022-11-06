package com.example.lambcamv1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lambcamv1.R
import com.example.lambcamv1.models.Lamb
import com.example.lambcamv1.fragments.lambListFragments.LambListFragmentDirections
import kotlinx.android.synthetic.main.lamb_custom_row.view.*

class LambListAdapter: RecyclerView.Adapter<LambListAdapter.MyLambViewHolder>() {

    // Create list of Lamb objects
    private var lambList = emptyList<Lamb>()

    // Initiate view holder
    class MyLambViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LambListAdapter.MyLambViewHolder {
        return MyLambViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lamb_custom_row, parent, false))
    }

    // Fill rows with lamb data
    override fun onBindViewHolder(lambHolder: LambListAdapter.MyLambViewHolder, position: Int) {
        val currentLamb = lambList[position]
        lambHolder.itemView.lambNoTextView.text = currentLamb.marking.toString()
        lambHolder.itemView.lambSexTextView.text = currentLamb.sex.toString()
        lambHolder.itemView.numberOfLambsTextView.text = currentLamb.siblings.toString()
        lambHolder.itemView.dateBornTextView.text = currentLamb.DOB.toString()

        // Navigation controller to view update fragment when row is clicked
        lambHolder.itemView.lambRowLayout.setOnClickListener {
            val action = LambListFragmentDirections.actionLambListFragmentToUpdateLambFragment(currentLamb)
            lambHolder.itemView.findNavController().navigate(action)
        }
    }

    // Count length of list of Lamb objects
    override fun getItemCount(): Int {
        return lambList.size
    }

    fun setData(lamb: List<Lamb>){
        this.lambList = lamb
        notifyDataSetChanged()
    }

}