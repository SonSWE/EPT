package com.example.ept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.ept.adapter.DateAdapter
import com.example.ept.model.DateModel

class StartWorkoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_workout)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.rvDate)


        // ArrayList of class ItemsViewModel
        val data = ArrayList<DateModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..60) {
            data.add(DateModel(i.toString(), false,  true))
        }
        data.add(DateModel("61", true, false))
        data.add(DateModel("62", false, false))

        // This will pass the ArrayList to our Adapter
        val adapter = DateAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }
}