package com.example.philosophicalconquest.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.philosophicalconquest.R
import com.example.philosophicalconquest.models.PhilosophyCell
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.recycler_view_cell_philosophy.view.*

class PhilosophyAdapter() :
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
                holder.view.philosophy_cell_owned.text = " "
                holder.view.philosophy_cell_owned_amount.text = " "
            }
            1 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.philosophy)
                holder.view.philosophy_cell_cost.text = "15"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_2)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_2)
                holder.view.philosophy_cell_owned_amount.text = "0"
            }
            2 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.greek3)
                holder.view.philosophy_cell_cost.text = "60"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_3)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_3)
                holder.view.philosophy_cell_owned_amount.text = "0"
            }
            3 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.greek2)
                holder.view.philosophy_cell_cost.text = "150"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_4)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_4)
                holder.view.philosophy_cell_owned_amount.text = "0"
            }
            4 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.roman)
                holder.view.philosophy_cell_cost.text = "650"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_5)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_5)
                holder.view.philosophy_cell_owned_amount.text = "0"
            }
            5 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.museum)
                holder.view.philosophy_cell_cost.text = "3 500"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_6)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_6)
                holder.view.philosophy_cell_owned_amount.text = "0"
            }
            6 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.forum)
                holder.view.philosophy_cell_cost.text = "20 000"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_7)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_7)
                holder.view.philosophy_cell_owned_amount.text = "0"
            }
            7 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.library)
                holder.view.philosophy_cell_cost.text = "170 000"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_8)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_8)
                holder.view.philosophy_cell_owned_amount.text = "0"
            }
            8 -> {
                holder.view.philosophy_cell_image.setImageResource(R.drawable.school)
                holder.view.philosophy_cell_cost.text = "700 000"
                holder.view.philosophy_cell_income.text =
                    holder.view.context.getString(R.string.philosophy_income_9)
                holder.view.philosophy_cell_title.text =
                    holder.view.context.getString(R.string.philosophy_cell_9)
                holder.view.philosophy_cell_owned.text = holder.view.context.getString(R.string.philosophy_owned)
                holder.view.philosophy_cell_owned_amount.text = "0"
            }
        }

        holder.view.philosophy_cell_card_view.setOnClickListener {
            val money = holder.view.rootView.game_money_text_view
            val amount = holder.view.philosophy_cell_owned_amount
            when (position) {
                0 -> {
                    money.text = (money.text.toString().toInt() + 1).toString()
                }
                1 -> {
                    if (money.text.toString().toInt() >= 15) {
                        money.text = (money.text.toString().toInt() - 15 ).toString()
                        amount.text = (amount.text.toString().toInt() + 1).toString()
                    }
                }
                2 -> {
                    if (money.text.toString().toInt() >= 60) {
                        money.text = (money.text.toString().toInt() - 60 ).toString()
                        amount.text = (amount.text.toString().toInt() + 1).toString()
                    }
                }
                3 -> {
                    if (money.text.toString().toInt() >= 150) {
                        money.text = (money.text.toString().toInt() - 150 ).toString()
                        amount.text = (amount.text.toString().toInt() + 1).toString()
                    }
                }
                4 -> {
                    if (money.text.toString().toInt() >= 650) {
                        money.text = (money.text.toString().toInt() - 650 ).toString()
                        amount.text = (amount.text.toString().toInt() + 1).toString()
                    }
                }
                5 -> {
                    if (money.text.toString().toInt() >= 3500) {
                        money.text = (money.text.toString().toInt() - 650 ).toString()
                        amount.text = (amount.text.toString().toInt() + 1).toString()
                    }
                }
                6 -> {
                    if (money.text.toString().toInt() >= 20000) {
                        money.text = (money.text.toString().toInt() - 650 ).toString()
                        amount.text = (amount.text.toString().toInt() + 1).toString()
                    }
                }
                7 -> {
                    if (money.text.toString().toInt() >= 170000) {
                        money.text = (money.text.toString().toInt() - 650 ).toString()
                        amount.text = (amount.text.toString().toInt() + 1).toString()
                    }
                }
                8 -> {
                    if (money.text.toString().toInt() >= 700000) {
                        money.text = (money.text.toString().toInt() - 650 ).toString()
                        amount.text = (amount.text.toString().toInt() + 1).toString()
                    }
                }
            }
        }
    }
}