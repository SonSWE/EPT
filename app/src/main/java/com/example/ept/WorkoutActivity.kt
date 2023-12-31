package com.example.ept

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.ept.model.ExerciseInfo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class WorkoutActivity : AppCompatActivity() {
    lateinit var _lstExercise: MutableList<ExerciseInfo>
    lateinit var Curent_Exercise: ExerciseInfo
    var _curentExerciseIndex: Int = 0

    private lateinit var youTubePlayer: YouTubePlayer

    private var isFullscreen = false
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isFullscreen) {
                // if the player is in fullscreen, exit fullscreen
                youTubePlayer.toggleFullscreen()
            } else {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        _lstExercise = intent.getSerializableExtra("exercise_list") as MutableList<ExerciseInfo>
        _curentExerciseIndex = intent.getIntExtra("curent_exercise_index", -1)

        Curent_Exercise = _lstExercise[_curentExerciseIndex]

        //<editor-fold desc="youtubeView">
        onBackPressedDispatcher.addCallback(onBackPressedCallback)

        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.video_tutorial)
        val fullscreenViewContainer = findViewById<FrameLayout>(R.id.full_screen_view_container)

        val iFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(1)
            .fullscreen(1) // enable full screen button
            .build()

        // we need to initialize manually in order to pass IFramePlayerOptions to the player
        youTubePlayerView.enableAutomaticInitialization = false

        youTubePlayerView.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullscreen = true

                // the video will continue playing in fullscreenView
                youTubePlayerView.visibility = View.GONE
                fullscreenViewContainer.visibility = View.VISIBLE
                fullscreenViewContainer.addView(fullscreenView)

                // optionally request landscape orientation
                //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onExitFullscreen() {
                isFullscreen = false

                // the video will continue playing in the player
                youTubePlayerView.visibility = View.VISIBLE
                fullscreenViewContainer.visibility = View.GONE
                fullscreenViewContainer.removeAllViews()

                //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        })

        youTubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@WorkoutActivity.youTubePlayer = youTubePlayer
                Curent_Exercise.video_Id?.let { youTubePlayer.cueVideo(it, 0f) }
            }
        }, iFramePlayerOptions)

        lifecycle.addObserver(youTubePlayerView)
        //</editor-fold>

        LoadContenExercise()
    }

    fun LoadContenExercise() {
        // Hiển thị fragment thích hợp
        if (Curent_Exercise.type == 1) {
            loadFragment(WorkoutByRepsFragment())
        } else if (Curent_Exercise.type == 2) {
            loadFragment(WorkoutByMinFragment())
        }
    }

    fun NextExercise() {
        if (_curentExerciseIndex + 1 == _lstExercise.size) {
            SuccessExercise()
        } else {
            _curentExerciseIndex += 1
            Curent_Exercise = _lstExercise[_curentExerciseIndex]
            Curent_Exercise.video_Id?.let { youTubePlayer.cueVideo(it, 0f) }

            loadFragment(BreakTimeFragment())
        }

    }

    fun SuccessExercise() {
        val succesIntent = Intent(this, LessonDoneActivity::class.java)
        startActivity(succesIntent)
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager

        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.container, fragment)

        fragmentTransaction.commit()
    }
}