package com.example.ept

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val intent = Intent(this, StartWorkoutActivity::class.java)
//        startActivity(intent)

//        val intent = Intent(this, WorkoutByMinActivity::class.java)
//        startActivity(intent

        val intent = Intent(this, WorckoutBySetsActivity::class.java)
        startActivity(intent)
    }
}