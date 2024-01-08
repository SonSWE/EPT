package com.example.ept

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.ept.DataAccess.UserDa
import com.example.ept.model.UserInfo
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.runBlocking

class SignupActivity : AppCompatActivity() {
    lateinit var database: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        val btnSignup = findViewById<MaterialButton>(R.id.btnSignup)
        btnSignup.setOnClickListener(View.OnClickListener {
            var tvUsername = findViewById<TextView>(R.id.tvUsername)
            var tvName = findViewById<TextView>(R.id.tvName)
            var tvPassword = findViewById<TextView>(R.id.tvPassword)

            var user = UserInfo(
                0,
                tvUsername.text.toString(),
                tvPassword.text.toString(),
                tvName.text.toString(),
            )

            var _result = UserDa().SignUp(user)
            if (_result > 0) {
                Toast.makeText(
                    this, "Đăng ký thành công!",
                    Toast.LENGTH_LONG
                ).show();
                finish()
            } else if (_result == -2) {
                Toast.makeText(
                    this@SignupActivity, "Tên đăng nhập đã tồn tại!",
                    Toast.LENGTH_LONG
                ).show();
            } else {
                Toast.makeText(
                    this, "Đăng ký thất bại!",
                    Toast.LENGTH_LONG
                ).show();
            }
        })


    }
}