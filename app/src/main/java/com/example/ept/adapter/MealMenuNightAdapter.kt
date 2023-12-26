package com.example.ept.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ept.R
import com.example.ept.model.Food
import com.example.ept.model.MealNightModel
import com.example.ept.model.MealMorningModel

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.NumberFormat


class MealMenuNightAdapter(private val mListFoodLun: List<MealNightModel>?) :
    RecyclerView.Adapter<MealMenuNightAdapter.MealMenuNightholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealMenuNightholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_morning, parent, false)
        return MealMenuNightholder(view)
    }

    override fun onBindViewHolder(holder: MealMenuNightholder, position: Int) {
        val food = mListFoodLun!![position] ?: return
        holder.amoutfoodMor.text = food.quantity.toString()
        holder.foodMor.text = food.name_food
        holder.kcalfoodMor.text = food.totalKcal.toString()

    }

    override fun getItemCount(): Int {
        return mListFoodLun?.size ?: 0
    }

    inner class MealMenuNightholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val amoutfoodMor: TextView
        val foodMor: TextView
        val kcalfoodMor: TextView


        init {
            amoutfoodMor = itemView.findViewById(R.id.amoutfoodMor)
            foodMor = itemView.findViewById(R.id.foodMor)
            kcalfoodMor = itemView.findViewById(R.id.kcalfoodMor)

        }



    }

    fun calculateTotalKcal(): Int {
        var totalKcal = 0

        for (food in mListFoodLun.orEmpty()) {
            totalKcal += food.totalKcal
        }


        return totalKcal
    }
}