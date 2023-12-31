package com.example.ept.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ept.ExerciseListActivity
import com.example.ept.R
import com.example.ept.databinding.ExerciseItemBinding
import com.example.ept.model.ExerciseInfo

class ExerciseAdapter(private val colection: List<ExerciseInfo>) :
    RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ExerciseItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return ExerciseViewHolder(view)

    }

    override fun getItemCount() = colection.size

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.binding.apply {
            val colection = colection[position]
            tvLessonName.text = colection.exercise_Name
            tvLessonDetail.text = colection.description

            Glide.with(this.root).load(colection.thumbnail).into(ivThumbnail)

            cardLesson.setOnClickListener { v ->
//                val intent = Intent(v.context, ExerciseListActivity::class.java)
//                intent.putExtra("Lesson_Id", colection.exercise_Id)
//                v.context.startActivity(intent)
            }
        }


    }
}
