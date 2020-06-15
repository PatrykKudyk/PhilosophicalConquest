package com.example.philosophicalconquest.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.philosophicalconquest.R
import com.example.philosophicalconquest.models.PhilosophyCell

class PhilosofyAdapter(val cells: Array<PhilosophyCell>) : RecyclerView.Adapter<PhilosophyViewHolder>() {

    override fun getItemCount(): Int {
        return cells.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhilosophyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.apartment_show_details, parent, false)
        return PhilosophyViewHolder(cellForRow)
    }



    override fun onBindViewHolder(holder: PhilosophyViewHolder, position: Int) {
        
    }
}