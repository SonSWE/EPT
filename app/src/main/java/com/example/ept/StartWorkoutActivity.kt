package com.example.ept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ept.adapter.DateAdapter
import com.example.ept.adapter.ExerciseAdapter
import com.example.ept.model.DateInLessonInfo
import com.example.ept.model.DateWorkoutInfo
import com.example.ept.model.ExerciseInfo
import com.example.ept.model.ExerciseLessonInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class StartWorkoutActivity : AppCompatActivity() {
    lateinit var rvDate: RecyclerView

    lateinit var dateAdapter: DateAdapter

    lateinit var _lstDate: MutableList<DateInLessonInfo>

    lateinit var database: DatabaseReference

    var Lesson_Id: Int = 0

    var _user_id: Int? = 0

    val isContinue: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_workout)
        database = FirebaseDatabase.getInstance().reference
        Lesson_Id = intent.getIntExtra("Lesson_Id", -1)
        val Lesson_Name = intent.getStringExtra("Lesson_Name").toString()
        val Lesson_Desc = intent.getStringExtra("Lesson_Desc").toString()
        val Thumbnail = intent.getStringExtra("Thumbnail").toString()
        val ivThumbnail: ImageView = findViewById(R.id.ivThumbnail)
        val tvName: TextView = findViewById(R.id.tvLessonName)
        val tvDesc: TextView = findViewById(R.id.tvDesc)
        tvName.text = Lesson_Name
        tvDesc.text = Lesson_Desc
        if (Thumbnail != null && Thumbnail != "") {
            Glide.with(this).load(Thumbnail).into(ivThumbnail)
        }

        LoadData()
    }

    private fun LoadData() {
        rvDate = findViewById(R.id.rvDate)
        _lstDate = ArrayList<DateInLessonInfo>()

        dateAdapter = DateAdapter(_lstDate)
        rvDate.adapter = dateAdapter

        val myRef = database.child("Workout")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //danh sách ngày tập theo giáo án
                _lstDate!!.clear()

                for (dataSnapshot in snapshot.child("Date_In_Lesson").children) {
                    val model = dataSnapshot.getValue<DateInLessonInfo>()
                    if (model != null && model.lesson_Id == Lesson_Id) {
                        _lstDate!!.add(model)
                    }
                }

                // Thông báo cho Adapter biết là dữ liệu đã thay đổi
                dateAdapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })


    }
}