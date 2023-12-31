package com.example.ept

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ept.model.ExerciseInfo
import com.google.android.material.button.MaterialButton
import java.util.Locale

class BreakTimeFragment : Fragment() {
    private var mTextViewCountDown: TextView? = null
    private var mButtonAddTime: MaterialButton? = null

    private var mCountDownTimer: CountDownTimer? = null

    private var mTimeLeftInSeconds: Long = 30
    private var mTimeLeftInMillis: Long = 0
    private var mEndTime: Long = 0

    private lateinit var activity: WorkoutActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_break_time, container, false)
        activity = getActivity() as WorkoutActivity

        val tvName = rootView.findViewById<TextView>(R.id.tvName)
        tvName.text = activity.Curent_Exercise.exercise_Name

        val tvDesc = rootView.findViewById<TextView>(R.id.tvDesc)
        tvDesc.text = activity.Curent_Exercise.description

        val tvCountExercise = rootView.findViewById<TextView>(R.id.tvCountExercise)
        tvCountExercise.text =
            (activity._curentExerciseIndex).toString() + "/" + activity._lstExercise.size.toString()

        mTimeLeftInMillis = mTimeLeftInSeconds * 1000
        mTextViewCountDown = rootView.findViewById<TextView>(R.id.tvClockDown)
        mButtonAddTime = rootView.findViewById<MaterialButton>(R.id.btnAddTime)

        startTimer()
        mTextViewCountDown?.text = secondsToMinutesAndSeconds(mTimeLeftInSeconds)
        mButtonAddTime?.setOnClickListener(View.OnClickListener {
            addTime()
        })


        val btnNext: MaterialButton = rootView.findViewById<MaterialButton>(R.id.btnNext)
        btnNext.setOnClickListener(View.OnClickListener {
            activity.LoadContenExercise()
        })

        // Inflate the layout for this fragment
        return rootView
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