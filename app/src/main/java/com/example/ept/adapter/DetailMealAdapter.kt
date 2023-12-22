package com.example.ept.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.ept.R
import com.example.ept.model.Food
import java.text.NumberFormat


class DetailMealAdapter(private val mListFood: List<Food>?) :
    RecyclerView.Adapter<DetailMealAdapter.DetailFoodHolder>() {

    private val quantityMap = mutableMapOf<Int, Int>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailFoodHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return DetailFoodHolder(view)
    }

    override fun onBindViewHolder(holder: DetailFoodHolder, position: Int) {
        val food = mListFood!![position] ?: return
        holder.tvNameFood.text = food.name_food
        holder.tvNumKcal.text = food.kcal_food.toString()



        // Kiểm tra xem đường dẫn ảnh có giá trị hay không trước khi sử dụng Glide
        if (food.img_food != null && !food.img_food!!.isEmpty()) {
            Glide.with(holder.itemView.context)
                .load(food.img_food)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgFood)
        } else {
            // Nếu không có đường dẫn ảnh, có thể đặt ảnh mặc định hoặc xử lý theo ý muốn của bạn
        }


        holder.tvPlus.setOnClickListener {
            updateQuantityAndKcal(holder, position, 1)
        }

        holder.tvMinus.setOnClickListener {
            updateQuantityAndKcal(holder, position, -1)
        }

        // Hiển thị số lượng thực phẩm trong EditText
        val quantity = quantityMap[position] ?: 0
        holder.amount.setText(quantity.toString())
    }

    override fun getItemCount(): Int {
        return mListFood?.size ?: 0
    }

    inner class DetailFoodHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNameFood: TextView
        val tvNumKcal: TextView
        val amount: EditText
        val imgFood: ImageView
        val tvPlus: TextView
        val tvMinus: TextView

        init {
            tvNameFood = itemView.findViewById(R.id.nameFood)
            tvNumKcal = itemView.findViewById(R.id.numKcal)
            imgFood = itemView.findViewById(R.id.imgfood)
            amount = itemView.findViewById(R.id.amount)
            tvPlus  = itemView.findViewById(R.id.tvplus)
            tvMinus = itemView.findViewById(R.id.tvminus)
        }
    }

    private fun updateQuantityAndKcal(holder: DetailFoodHolder, position: Int, change: Int) {
        val currentQuantity = quantityMap[position] ?: 0
        var newQuantity = currentQuantity + change

        // Đảm bảo số lượng không âm
        if (newQuantity < 1) {
            newQuantity = 1
        }

        // Cập nhật số lượng trong Map
        quantityMap[position] = newQuantity

        // Cập nhật EditText và tính toán lại tổng kcal mới
        holder.amount.setText(newQuantity.toString())

        // Tính toán tổng kcal mới và cập nhật TextView
        val food = mListFood!![position]
        val totalKcal = newQuantity * (food.kcal_food?.toDouble() ?: 0.0)
        val formattedKcal = formatKcal(totalKcal)
        holder.tvNumKcal.text = formattedKcal
    }

    private fun formatKcal(value: Double): String {
        val numberFormat = NumberFormat.getInstance()
        return if (value.isWhole()) {
            numberFormat.format(value.toLong())
        } else {
            numberFormat.format(value)
        }
    }

    private fun Double.isWhole(): Boolean {
        return this % 1.0 == 0.0
    }

}