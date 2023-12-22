package com.example.ept

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.ept.model.AlarmReceiver
import java.util.Calendar
import java.util.Locale

class MealMenuActivity : AppCompatActivity() {

    var notificationReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if ("NOTIFICATION_SHOWN" == intent.action) {
                // Thông báo đã được hiển thị xong, thực hiện các thao tác cập nhật màu sắc ở đây
                updateTextColor()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_menu)

        val intentFilter = IntentFilter("NOTIFICATION_SHOWN")
        registerReceiver(notificationReceiver, intentFilter)

        // Khởi tạo TextView của bạn ở đây
        val myTextView = findViewById<TextView>(R.id.textNext)
        myTextView.setOnClickListener { v: View? ->
            // Tạo Intent để chuyển từ MainActivity sang một Activity khác (ActivityB)
            val intent = Intent(this@MealMenuActivity, WaterHistoryActivity::class.java)

            // Bắt đầu hoạt động mới
            startActivity(intent)
        }
        val DetailMead = findViewById<TextView>(R.id.detailMeal)
        DetailMead.setOnClickListener { v: View? ->
            // Tạo Intent để chuyển từ MainActivity sang một Activity khác (ActivityB)
            val intent = Intent(this@MealMenuActivity, DetailMealActivity::class.java)

            // Bắt đầu hoạt động mới
            startActivity(intent)
        }


        // Lắng nghe sự kiện khi nhấp vào TextView "Disable notification"
        val notificationDis = findViewById<TextView>(R.id.notificationDis)
        notificationDis.setOnClickListener { // Hiển thị hộp thoại chọn thời gian
            showTimePickerDialog()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Hủy đăng ký BroadcastReceiver khi Activity kết thúc
        unregisterReceiver(notificationReceiver)
    }

    private fun updateTextColor() {
        // Cập nhật màu sắc của TextView sau khi thông báo kết thúc
        val txAlarm = findViewById<TextView>(R.id.txAlarm)
        txAlarm.setTextColor(Color.parseColor("#CCCCCC"))
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentHour = calendar[Calendar.HOUR_OF_DAY]
        val currentMinute = calendar[Calendar.MINUTE]
        val timePickerDialog = TimePickerDialog(
            this,
            { view, hourOfDay, minute ->
                val formattedTime =
                    String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
                val txAlarm = findViewById<TextView>(R.id.txAlarm)
                txAlarm.text = formattedTime
                setAlarm(hourOfDay, minute)
            },
            currentHour,
            currentMinute,
            true
        )
        timePickerDialog.show()
    }

    private fun setAlarm(hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = hourOfDay
        calendar[Calendar.MINUTE] = minute
        calendar[Calendar.SECOND] = 0
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.putExtra("title", "Hãy uống nước đi")
        intent.putExtra("content", "Đến giờ uống nước rồi!")
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager?.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }
}