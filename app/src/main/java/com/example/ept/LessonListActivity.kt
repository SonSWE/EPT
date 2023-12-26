package com.example.ept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ept.adapter.LessonAdapter
import com.example.ept.adapter.LessonByMuscleAdapter
import com.example.ept.model.LessonModel
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.button.MaterialButton

class LessonListActivity : AppCompatActivity() {
    val isContinue: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)

        val btnContinue: MaterialButton = findViewById(R.id.btnContinue)
        val tvNote: TextView = findViewById(R.id.tvNote)
        val btnRestart: MaterialButton = findViewById(R.id.btnRestart)
        val btnStart: MaterialButton = findViewById(R.id.btnStart)
        val layoutBtnContinue: FlexboxLayout = findViewById(R.id.layoutBtnContinue)
        //load button by status
        if (isContinue) {
            layoutBtnContinue.visibility = View.VISIBLE
            btnRestart.visibility = View.VISIBLE
            tvNote.text = "13%" + "đã hoàn thành"
            btnStart.visibility = View.GONE
        } else {
            btnRestart.visibility = View.GONE
            btnRestart.visibility = View.GONE
            btnStart.visibility = View.VISIBLE
        }

        val recyclerview: RecyclerView = findViewById(R.id.rvLesson)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<LessonModel>()
        data.add(LessonModel(1, "Ngực", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Bụng 6 múi", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Thân dưới", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Lưng sô", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Ngực", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Bụng 6 múi", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Thân dưới", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Lưng sô", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Ngực", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Bụng 6 múi", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Thân dưới", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Lưng sô", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Ngực", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Bụng 6 múi", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Thân dưới", "30 phút -12 bài", 3, "abc"))
        data.add(LessonModel(1, "Lưng sô", "30 phút -12 bài", 3, "abc"))


        // This will pass the ArrayList to our Adapter
        val adapter = LessonAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}