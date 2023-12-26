package com.example.ept

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
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

                else -> {false}
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