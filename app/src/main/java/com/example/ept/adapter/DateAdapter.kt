package com.example.ept.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ept.R
import com.example.ept.databinding.DayItemBinding
import com.example.ept.model.DateModel


class DateAdapter(private val colection: List<DateModel>) :
    RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DayItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_item, parent, false)
        return DateViewHolder(view)

    }

    override fun getItemCount() = colection.size

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.binding.apply {
            val colection = colection[position]
            tvDay.text = colection.date

            if (colection.done) {
                cvDate.setBackgroundResource(R.drawable.card_done)
                holder.itemView.setOnClickListener { v ->
                    Toast.makeText(
                        v.context,
                        "Đã hoàn thành: " + colection.date,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                holder.isRecyclable()
            } else {
                if (colection.active) {
                    cvDate.setBackgroundResource(R.drawable.card_active)
                    tvDay.setTextColor(Color.parseColor("#ffffff"))
                    tvLabel.setTextColor(Color.parseColor("#ffffff"))

                    cvDate.setBackgroundResource(R.drawable.card_done)
                    holder.itemView.setOnClickListener { v ->
                        Toast.makeText(
                            v.context,
                            "Bắt đầu tập nào: " + colection.date,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    cvDate.setBackgroundResource(R.drawable.card_disable)
                    tvDay.setTextColor(Color.parseColor("#757575"))
                    tvLabel.setTextColor(Color.parseColor("#757575"))

                    cvDate.setBackgroundResource(R.drawable.card_done)
                    holder.itemView.setOnClickListener { v ->
                        Toast.makeText(
                            v.context,
                            "Chưa đến ngày: " + colection.date,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }


        }
    }
}