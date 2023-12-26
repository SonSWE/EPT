package com.example.ept.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ept.R
import com.example.ept.databinding.LessonPlanItemBinding
import com.example.ept.model.LessonModel

class LessonPlanAdapter(private val colection: List<LessonModel>) :
    RecyclerView.Adapter<LessonPlanAdapter.LessonPlanViewHolder>() {

    inner class LessonPlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = LessonPlanItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonPlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_plan_item, parent, false)
        return LessonPlanViewHolder(view)

    }

    override fun getItemCount() = colection.size

    override fun onBindViewHolder(holder: LessonPlanViewHolder, position: Int) {
        holder.binding.apply {
            val colection = colection[position]
            tvLessonName.text = colection.Name
            tvLessonDesc.text = colection.Description
        }


    }
}