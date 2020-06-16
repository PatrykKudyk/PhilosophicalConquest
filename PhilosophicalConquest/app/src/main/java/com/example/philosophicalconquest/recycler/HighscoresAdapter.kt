package com.example.philosophicalconquest.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.philosophicalconquest.R


class HighscoresAdapter(val highscores: Array<Int>, val type: Int) :
    RecyclerView.Adapter<HighscoresViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighscoresViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellRow = layoutInflater.inflate(R.layout.cell_highscore, parent, false)
        return HighscoresViewHolder(cellRow)
    }

    override fun getItemCount(): Int {
        return highscores.size
    }

    override fun onBindViewHolder(holder: HighscoresViewHolder, position: Int) {
        
    }

}


class HighscoresViewHolder(val view: View) : RecyclerView.ViewHolder(view)