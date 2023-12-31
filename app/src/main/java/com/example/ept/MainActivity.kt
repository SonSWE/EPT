package com.example.ept

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ept.model.DateInLessonInfo
import com.example.ept.model.ExerciseInfo
import com.example.ept.model.ExerciseLessonInfo
import com.example.ept.model.LessonInfo
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val intent = Intent(this, StartWorkoutActivity::class.java)
//        startActivity(intent)

//        val intent = Intent(this, WorkoutByMinActivity::class.java)
//        startActivity(intent)

//        val intent = Intent(this, LessonListActivity::class.java)
//        startActivity(intent)
        val database = FirebaseDatabase.getInstance().reference
//        val ref = database.child("Workout").child("Lesson")
//
//        val data = ArrayList<LessonInfo>()
//        data.add(
//            LessonInfo(
//                1,
//                "Xây dựng cơ bắp 7X4",
//                "Bắt đầu hành chình xây dựng cơ bắp toàn thân với 60 ngày tập luyện",
//                0,
//                1,
//                "https://center.gymaster.vn/wp-content/uploads/2020/01/shutterstock-410287138-1000x600.png"
//            )
//        )
//        data.add(
//            LessonInfo(
//                2,
//                "Tập bụng cơ bản",
//                "20 phút - 16 bài tập",
//                1,
//                2,
//                "https://swequity.vn/wp-content/uploads/2019/10/tap-co-bung.jpg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                3,
//                "Tập bụng nâng cao",
//                "29 phút - 14 bài tập",
//                3,
//                2,
//                "https://swequity.vn/wp-content/uploads/2019/10/tap-co-bung.jpg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                4,
//                "Tập ngực cơ bản",
//                "30 phút - 11 bài tập",
//                1,
//                2,
//                "https://bloganchoi.com/wp-content/uploads/2019/09/nam-day-ta-bang-thanh-don.jpg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                5,
//                "Tập ngực nâng cao",
//                "30 phút - 11 bài tập",
//                3,
//                2,
//                "https://bloganchoi.com/wp-content/uploads/2019/09/nam-day-ta-bang-thanh-don.jpg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                6,
//                "Tập lưng cơ bản",
//                "30 phút - 11 bài tập",
//                1,
//                2,
//                "https://gymhomies.com/wp-content/uploads/2021/09/bai-tap-lung-xo-lat-pulldown.jpeg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                7,
//                "Tập lưng nâng cao",
//                "30 phút - 11 bài tập",
//                3,
//                2,
//                "https://gymhomies.com/wp-content/uploads/2021/09/bai-tap-lung-xo-lat-pulldown.jpeg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                8,
//                "Tập chân cơ bản",
//                "30 phút - 11 bài tập",
//                1,
//                2,
//                "https://file.hstatic.net/1000189642/article/tonsilstoneshelper-fitness1.jpg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                9,
//                "Tập tay cơ bản",
//                "30 phút - 11 bài tập",
//                1,
//                2,
//                "https://file.hstatic.net/1000189642/article/tonsilstoneshelper-fitness1.jpg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                10,
//                "Tập chân nâng cao",
//                "30 phút - 11 bài tập",
//                3,
//                2,
//                "https://swequity.vn/wp-content/uploads/2019/09/tap-chan.jpg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                11,
//                "Tập tay nâng cao",
//                "30 phút - 11 bài tập",
//                3,
//                2,
//                "https://cdn-img.thethao247.vn/upload/thanhtung/2020/05/21/4-bai-tap-phuc-hop-bap-tay-truoc-1.jpg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                12,
//                "Tập vai cơ bản",
//                "30 phút - 11 bài tập",
//                1,
//                2,
//                "https://i.ytimg.com/vi/Fgz_FdzDukE/maxresdefault.jpg"
//            )
//        )
//        data.add(
//            LessonInfo(
//                13,
//                "Tập vai nâng cao",
//                "30 phút - 11 bài tập",
//                3,
//                2,
//                "https://www.dinhduongthehinh.com/wp-content/uploads/2021/05/cach-tap-vai.jpg"
//            )
//        )
//
//        for (item in data) {
//            val newRef = ref.push()
//            newRef.setValue(item)
//        }

//        val data2 = ArrayList<ExerciseInfo>()
//        data2.add(
//            ExerciseInfo(
//                3,
//                "Bật nhảy",
//                "4 hiệp x 12 lần tập",
//                20,
//                2,
//                "https://cdn.dribbble.com/users/2931468/screenshots/5720362/media/c676b4da711c7ec96b67e027fd83a1af.gif",
//                "z3rl8PYf2Do",
//                "Đứng 2 chận rộng bằng, tay buông xuống, sau đó bật nhảy 2 chân rộng hơn vai đồng thời tay 2 tay đưa thẳng lên trời, lặp lại"
//            )
//        )
//        data2.add(
//            ExerciseInfo(
//                4,
//                "Tập cơ bụng",
//                "4 hiệp x 12 lần tập",
//                30,
//                1,
//                "https://cdn.dribbble.com/users/2931468/screenshots/5720362/media/c676b4da711c7ec96b67e027fd83a1af.gif",
//                "z3rl8PYf2Do",
//                "Đứng 2 chận rộng bằng, tay buông xuống, sau đó bật nhảy 2 chân rộng hơn vai đồng thời tay 2 tay đưa thẳng lên trời, lặp lại"
//            )
//        )
//        val ref = database.child("Workout").child("Exercise")
//        for (item in data2) {
//            val newRef = ref.push()
//            newRef.setValue(item)
//        }

//        val data2 = ArrayList<ExerciseLessonInfo>()
//        data2.add(
//            ExerciseLessonInfo(
//                3,
//                2,
//                3
//            )
//        )
//        data2.add(
//            ExerciseLessonInfo(
//                4,
//                2,
//                4
//            )
//        )
//
//        val ref = database.child("Workout").child("Exercise_Lesson")
//        for (item in data2) {
//            val newRef = ref.push()
//            newRef.setValue(item)
//        }

//        val ref = database.child("Workout").child("Date_In_Lesson")
//        //9 tuần
//        for (i in 1..9) {
//            //mỗi tuần 6 buổi
//            for (j in 1..6) {
//                var name: String = ""
//                var desc: String = ""
//                var day: Int = i * j
//
//                when (j) {
//                    1 -> {
//                        name = "Nhóm cơ ngực"
//                        desc = "40 phút - 8 bài tập"
//                    }
//
//                    2 -> {
//                        name = "Nhóm cơ lưng"
//                        desc = "35 phút - 5 bài tập"
//                    }
//
//                    3 -> {
//                        name = "Nhóm cơ chân"
//                        desc = "35 phút - 7 bài tập"
//                    }
//
//                    4 -> {
//                        name = "Nhóm cơ vai"
//                        desc = "35 phút - 5 bài tập"
//                    }
//
//                    5 -> {
//                        name = "Nhóm cơ tay"
//                        desc = "40 phút - 5 bài tập"
//                    }
//
//                    6 -> {
//                        name = "Tập cardio mức độ vừa"
//                        desc = "20 phút - 4 bài tập"
//                    }
//                }
//                val data2 = ArrayList<DateInLessonInfo>()
//                data2.add(
//                    DateInLessonInfo(
//                        day,
//                        1,
//                        day,
//                        name,
//                        desc,
//                        0,
//                        0,
//                        "https://bloganchoi.com/wp-content/uploads/2019/09/nam-day-ta-bang-thanh-don.jpg",
//                        0
//                    )
//                )
//
//                for (item in data2) {
//                    val newRef = ref.push()
//                    newRef.setValue(item)
//                }
//            }
//        }

        loadFragment(DashboardFragment())
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.dashboardFragment -> {
                    loadFragment(DashboardFragment())
                    true
                }

                R.id.mealFragment -> {
                    loadFragment(MealFragment())
                    true
                }
//
//                R.id.settings -> {
//                    loadFragment(DashboardFragment())
//                    true
//                }

                else -> {
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Get a reference to the FragmentManager
        val fragmentManager = supportFragmentManager

        // Start a new FragmentTransaction
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Replace the current fragment with the new fragment
        fragmentTransaction.replace(R.id.container, fragment)

        // Commit the FragmentTransaction
        fragmentTransaction.commit()
    }
}