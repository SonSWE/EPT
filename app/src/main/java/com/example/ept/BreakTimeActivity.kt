package com.example.ept

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import java.util.Locale

class BreakTimeActivity : AppCompatActivity() {

    private var mTextViewCountDown: TextView? = null
    private var mButtonAddTime: MaterialButton? = null

    private var mCountDownTimer: CountDownTimer? = null

    private var mTimeLeftInSeconds: Long = 30
    private var mTimeLeftInMillis: Long = 0
    private var mEndTime: Long = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_break_time)


        mTimeLeftInMillis = mTimeLeftInSeconds * 1000
        mTextViewCountDown = findViewById<TextView>(R.id.tvClockDown)
        mButtonAddTime = findViewById<MaterialButton>(R.id.btnAddTime)

        startTimer()
        mTextViewCountDown?.text = secondsToMinutesAndSeconds(mTimeLeftInSeconds)
        mButtonAddTime?.setOnClickListener(View.OnClickListener {
            addTime()

        })

        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            finish()
        }
        val negativeButtonClick = { dialog: DialogInterface, which: Int ->

        }

        val btnNext: MaterialButton = findViewById<MaterialButton>(R.id.btnNext)
        btnNext.setOnClickListener(View.OnClickListener {
//            if(!isDone){
//                val builder = AlertDialog.Builder(this)
//
//                builder.setTitle("Thông báo")
//                builder.setMessage("Bạn phải hoàn thành bài tập này trước khi chuyển sang bài tập tiếp theo")
//                builder.setPositiveButton("Đồng ý", negativeButtonClick)
//
//                val alertDialog = builder.create()
//                alertDialog.show()
//            }
        })
    }

    fun secondsToMinutesAndSeconds(seconds: Long): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    private fun startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis * 1000
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {

            }
        }.start()
    }

    private fun addTime() {
        mCountDownTimer!!.cancel()
        //+ 20 giây
        mTimeLeftInMillis = mTimeLeftInMillis + 20000
        startTimer()
    }

    private fun updateCountDownText() {
        val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        mTextViewCountDown?.text = timeLeftFormatted
    }
}