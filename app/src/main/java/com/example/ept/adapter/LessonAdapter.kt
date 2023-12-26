package com.example.ept.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ept.R
import com.example.ept.databinding.LessonItemBinding
import com.example.ept.model.LessonModel

class LessonAdapter(private val colection: List<LessonModel>) :
    RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = LessonItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false)
        return LessonViewHolder(view)

    }

    override fun getItemCount() = colection.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.binding.apply {
            val colection = colection[position]
            tvLessonName.text = colection.Name
            tvLessonDetail.text = colection.Description
        }


    }
}
