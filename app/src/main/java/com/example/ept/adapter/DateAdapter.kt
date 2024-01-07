package com.example.ept.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ept.DateWorkoutListActivity
import com.example.ept.ExerciseListActivity
import com.example.ept.R
import com.example.ept.databinding.DayItemBinding
import com.example.ept.model.DateInLessonInfo
import com.example.ept.model.LessonInfo


class DateAdapter(private val activity: DateWorkoutListActivity, private val colection: List<DateInLessonInfo>, private val  lesson: LessonInfo) :
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
            tvDay.text = colection.day.toString()
            if(colection.status == 0){
                //khóa
                cvDate.setBackgroundResource(R.drawable.card_disable)
            }else if(colection.status == 1){
                //đã tập
                cvDate.setBackgroundResource(R.drawable.card_done)
            }else if(colection.status == 2){
                //chưa tập
                cvDate.setBackgroundResource(R.drawable.card_active)
                cvDate.setOnClickListener {
                    activity.goExerciseList()
                }
            }
        }
    }
}