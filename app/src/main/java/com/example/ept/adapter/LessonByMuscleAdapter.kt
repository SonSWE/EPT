package com.example.ept.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ept.R
import com.example.ept.databinding.LessonByMuscleItemBinding
import com.example.ept.model.LessonModel


class LessonByMuscleAdapter(private val colection: List<LessonModel>) :
    RecyclerView.Adapter<LessonByMuscleAdapter.LessonByMuscleViewHolder>() {

    inner class LessonByMuscleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = LessonByMuscleItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonByMuscleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_by_muscle_item, parent, false)
        return LessonByMuscleViewHolder(view)

    }

    override fun getItemCount() = colection.size

    override fun onBindViewHolder(holder: LessonByMuscleViewHolder, position: Int) {
        holder.binding.apply {
            val colection = colection[position]
            tvLessonName.text = colection.Name
            tvLessonDesc.text = colection.Description
        }


    }
}
