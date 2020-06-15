package com.example.philosophicalconquest.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.philosophicalconquest.R
import com.example.philosophicalconquest.models.PhilosophyCell
import kotlinx.android.synthetic.main.recycler_view_cell_philosophy.view.*

class PhilosofyAdapter() :
    RecyclerView.Adapter<PhilosophyViewHolder>() {

    override fun getItemCount(): Int {
        return 9
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhilosophyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow =
            layoutInflater.inflate(R.layout.recycler_view_cell_philosophy, parent, false)
        return PhilosophyViewHolder(cellForRow)
    }


    override fun onBindViewHolder(holder: PhilosophyViewHolder, position: Int) {

        when (position) {
            0 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.philosopher)
                holder.view.philosophy_cell_cost.text = "   "
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_1)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_1)
            }
            1 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.philosophy)
                holder.view.philosophy_cell_cost.text = "15"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_2)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_2)
            }
            2 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.greek3)
                holder.view.philosophy_cell_cost.text = "60"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_3)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_3)
            }
            3 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.greek2)
                holder.view.philosophy_cell_cost.text = "150"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_4)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_4)
            }
            4 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.roman)
                holder.view.philosophy_cell_cost.text = "650"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_5)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_5)
            }
            5 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.museum)
                holder.view.philosophy_cell_cost.text = "3 500"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_6)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_6)
            }
            6 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.forum)
                holder.view.philosophy_cell_cost.text = "20 000"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_7)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_7)
            }
            7 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.library)
                holder.view.philosophy_cell_cost.text = "170 000"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_8)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_8)
            }
            8 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.school)
                holder.view.philosophy_cell_cost.text = "700 000"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_9)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_9)
            }
        }
    }

    private fun getStringIdentifier(context: Context, name: String): Int {
        return context.resources.getIdentifier(name, "string", context.packageName)
    }
}