package com.example.ept

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ept.adapter.LessonByMuscleAdapter
import com.example.ept.adapter.LessonPlanAdapter
import com.example.ept.model.LessonModel


class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val recyclerview: RecyclerView = rootView.findViewById(R.id.rvLesson)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<LessonModel>()
        data.add(LessonModel(1, "Ngực",  "30 phút -12 bài",3,"abc"))
        data.add(LessonModel(1, "Bụng 6 múi",  "30 phút -12 bài",3,"abc"))
        data.add(LessonModel(1, "Thân dưới",  "30 phút -12 bài",3,"abc"))
        data.add(LessonModel(1, "Lưng sô",  "30 phút -12 bài",3,"abc"))


        // This will pass the ArrayList to our Adapter
        val adapter = LessonByMuscleAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter



        val recyclerviewLessonPlan:RecyclerView = rootView.findViewById(R.id.rvLessonByPlan)

        val data2 = ArrayList<LessonModel>()
        data.add(LessonModel(1, "Ngực",  "30 phút -12 bài",3,"abc"))

        val lessonPlanAdapter = LessonPlanAdapter(data)
        recyclerviewLessonPlan.adapter = lessonPlanAdapter

        // Inflate the layout for this fragment
        return rootView
    }
}