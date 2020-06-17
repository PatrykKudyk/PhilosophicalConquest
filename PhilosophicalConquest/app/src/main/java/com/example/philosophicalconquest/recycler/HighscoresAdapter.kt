package com.example.philosophicalconquest.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.philosophicalconquest.R
import com.example.philosophicalconquest.models.Score
import kotlinx.android.synthetic.main.cell_highscore.view.*
import kotlinx.android.synthetic.main.fragment_win.view.*


class HighscoresAdapter(val highscores: List<Score>, val type: Int) :
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
        holder.view.cell_text_view_place.text = (position + 1).toString() +"."
        if (highscores[position].time % 60 == 0) {
            holder.view.cell_text_view_time.text = (highscores[position].time / 60).toString() + ":00"
        } else if (highscores[position].time % 60 < 10) {
            holder.view.cell_text_view_time.text =
                (highscores[position].time / 60).toString() + ":0" + (highscores[position].time % 60).toString()
        } else {
            holder.view.cell_text_view_time.text =
                (highscores[position].time / 60).toString() + ":" + (highscores[position].time % 60).toString()
        }
        holder.view.cell_text_view_name.text = highscores[position].nick
    }

}


class HighscoresViewHolder(val view: View) : RecyclerView.ViewHolder(view)