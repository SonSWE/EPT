package com.example.ept

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.ept.DataAccess.UserDa
import com.example.ept.Utils.UserShareReferentHelper
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
        btnLogin.setOnClickListener(View.OnClickListener {
            var tvUsername = findViewById<TextView>(R.id.tvUsername)
            var tvPassword = findViewById<TextView>(R.id.tvPassword)

            var _user = UserDa().Login(tvUsername.text.toString(), tvPassword.text.toString())
            if (_user != null && _user.user_Name != "") {
                Toast.makeText(
                    this, "Đăng nhập thành công!",
                    Toast.LENGTH_LONG
                ).show();

                UserShareReferentHelper().saveUser(_user, this)

                val mainActivity = Intent(this, MainActivity::class.java)
                startActivity(mainActivity)
            } else {
                Toast.makeText(
                    this, "Sai tài khoản hoặc mật khẩu!",
                    Toast.LENGTH_LONG
                ).show();
            }
        })

        val btnGoSignup = findViewById<MaterialButton>(R.id.btnGoSignup)
        btnGoSignup.setOnClickListener(View.OnClickListener {
            val signup = Intent(this, SignupActivity::class.java)
            startActivity(signup)
        })
    }




}