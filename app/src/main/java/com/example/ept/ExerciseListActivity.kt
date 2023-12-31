package com.example.ept

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ept.adapter.ExerciseAdapter
import com.example.ept.model.ExerciseLessonInfo
import com.example.ept.model.ExerciseInfo
import com.example.ept.model.ResultInfo
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import java.io.Serializable

class ExerciseListActivity : AppCompatActivity() {
    lateinit var rvExercise: RecyclerView
    lateinit var exerciseAdapter: ExerciseAdapter
    lateinit var _lstExercise: MutableList<ExerciseInfo>

    lateinit var _result: ResultInfo
    var _next_exercise_index: Int = 0

    lateinit var database: DatabaseReference

    var Lesson_Id: Int = 0
    var Lesson_Name: String = ""
    var Lesson_Desc: String = ""
    var Thumbnail: String = ""
    var Date_Id: Int = 0

    var _user_id: Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_list)
        database = FirebaseDatabase.getInstance().reference
        _user_id = 1

        Lesson_Name = intent.getStringExtra("Lesson_Name").toString()
        Lesson_Desc = intent.getStringExtra("Lesson_Desc").toString()
        Thumbnail = intent.getStringExtra("Thumbnail").toString()
        Lesson_Id = intent.getIntExtra("Lesson_Id", -1)
        Date_Id = intent.getIntExtra("Date_Id", -1)

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
        rvExercise = findViewById(R.id.rvExercise)

        _lstExercise = ArrayList<ExerciseInfo>()
        _result = ResultInfo()

        exerciseAdapter = ExerciseAdapter(_lstExercise)
        rvExercise.adapter = exerciseAdapter

        val myRef = database.child("Workout")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (dataSnapshot in snapshot.child("Result").children) {
                    val model = dataSnapshot.getValue<ResultInfo>()

                    if (Date_Id > 0) {
                        //lấy kết quả tập hiện tạo theo ngày tập từ giáo trình
                        if (model != null && model.date_In_Lesson_Id == Date_Id) {
                            _result = model
                        }
                    } else {
                        //lấy kết quả tập từ bài tập theo nhóm cơ
                        if (model != null && model.lesson_Id == Lesson_Id) {
                            _result = model
                        }
                    }
                }

                //danh sách id bài tập theo giáo án
                val lstExerciseLesson: ArrayList<ExerciseLessonInfo> =
                    ArrayList<ExerciseLessonInfo>()
                for (dataSnapshot in snapshot.child("Exercise_Lesson").children) {
                    val model = dataSnapshot.getValue<ExerciseLessonInfo>()
                    if (model != null && model.lesson_Id == Lesson_Id) {
                        lstExerciseLesson.add(model)
                    }
                }

                //danh sách bài tập
                _lstExercise!!.clear()

                for (dataSnapshot in snapshot.child("Exercise").children) {
                    val model = dataSnapshot.getValue<ExerciseInfo>()
                    if (model != null) {
                        if (lstExerciseLesson.any { x -> x.exercise_Id == model.exercise_Id }) {
                            _lstExercise!!.add(model)
                        }

                    }
                }

                if (_result != null) {
                    _next_exercise_index =
                        _lstExercise.indexOfFirst { x -> x.exercise_Id == _result?.current_Exercise_Id }
                            ?: 0
                }
                // Thông báo cho Adapter biết là dữ liệu đã thay đổi
                exerciseAdapter!!.notifyDataSetChanged()

                LoadUi()
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })


    }

    private fun LoadUi() {
        val tvNote: TextView = findViewById(R.id.tvNote)
        val btnRestart: MaterialButton = findViewById(R.id.btnRestart)
        val btnStart: MaterialButton = findViewById(R.id.btnStart)
        val btnContinue: MaterialButton = findViewById(R.id.btnContinue)
        val layoutBtnContinue: FlexboxLayout = findViewById(R.id.layoutBtnContinue)
        //load button by status
        if (_result != null) {
            if (_result.status == 1) {
                //đang tập dở
                layoutBtnContinue.visibility = View.VISIBLE
                btnRestart.visibility = View.VISIBLE
                tvNote.text = "Đã tập " + _result.count_Done.toString() + "/" + _lstExercise.count()
                    .toString() + " bài"
                btnStart.visibility = View.GONE

                btnContinue.setOnClickListener {
                    StatrWorkout(_lstExercise, 0);
                }
            }
        } else {
            //chưa tập gì
            btnRestart.visibility = View.GONE
            btnRestart.visibility = View.GONE
            btnStart.visibility = View.VISIBLE

            StatrWorkout(_lstExercise, 0);
        }
    }

    fun StatrWorkout(mListExercise: MutableList<ExerciseInfo>, curent_exercise_index: Int) {
        val intent = Intent(this, WorkoutActivity::class.java)

        intent.putExtra("exercise_list", mListExercise as Serializable)
        intent.putExtra("curent_exercise_index", curent_exercise_index)

        startActivity(intent)
    }
}