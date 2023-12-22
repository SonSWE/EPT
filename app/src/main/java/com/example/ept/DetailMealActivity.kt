package com.example.ept

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ept.adapter.DetailMealAdapter
import com.example.ept.model.Food
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DetailMealActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var mDetailMealAdapter: DetailMealAdapter? = null
    private var mListFood: MutableList<Food>? = null
    var name: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_meal)

        val bottomSheet = findViewById<FrameLayout>(R.id.sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

// Thiết lập chiều cao tối thiểu (peek height) và trạng thái ban đầu
        bottomSheetBehavior.peekHeight = 80
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        // Khởi tạo UI và danh sách thực phẩm
        initUi()

        // Bắt sự kiện khi người dùng nhấn nút ImageView
        val myImageView = findViewById<ImageView>(R.id.myImageView)
        myImageView.setOnClickListener { v: View? ->
            val intent = Intent(this@DetailMealActivity, MealMenuActivity::class.java)
            startActivity(intent)
        }

        // Lấy danh sách thực phẩm từ cơ sở dữ liệu Firebase
        listFoodDatabase
    }

    private fun initUi() {
        // Khởi tạo RecyclerView và Adapter
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewFood)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        // Khởi tạo danh sách thực phẩm
        mListFood = ArrayList()
        mDetailMealAdapter = DetailMealAdapter(mListFood)
        recyclerView.setAdapter(mDetailMealAdapter)
    }

    private val listFoodDatabase: Unit
        private get() {
            // Kết nối đến cơ sở dữ liệu Firebase
            val database = FirebaseDatabase.getInstance()
            val myRef = database.reference.child("food")

            // Lắng nghe sự thay đổi trong dữ liệu
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Xóa danh sách cũ trước khi cập nhật
                    mListFood!!.clear()

                    // Duyệt qua dữ liệu mới và thêm vào danh sách
                    for (dataSnapshot in snapshot.children) {
                        val food = dataSnapshot.getValue(Food::class.java)
                        if (food != null) {
                            food.img_food = dataSnapshot.child("img_food").getValue(
                                String::class.java
                            )
                            mListFood!!.add(food)
                        }
                    }


                    // Thông báo cho Adapter biết là dữ liệu đã thay đổi
                    mDetailMealAdapter!!.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    // Xử lý khi có lỗi xảy ra
                    Toast.makeText(
                        this@DetailMealActivity,
                        "Lấy danh sách thực phẩm thất bại!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
}