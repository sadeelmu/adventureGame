package com.example.adventuregame

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import com.example.adventuregame.databinding.ActivityGameScreenBinding
import java.util.Timer
import kotlin.math.roundToInt

class GameScreen : AppCompatActivity() {

    private lateinit var binding: ActivityGameScreenBinding

    private var timerStarted = false
    private lateinit var serviceIntent:Intent
    private var time:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameTV: TextView = findViewById(R.id.timeTV)
        val gameIV: ImageView = findViewById(R.id.gameIV)
        val choice1BT: Button = findViewById(R.id.choice1BT)
        val choice2BT: Button = findViewById(R.id.choice2BT)
        val choice3BT: Button = findViewById(R.id.choice3BT)
        val choice4BT: Button = findViewById(R.id.choice4BT)

        val timerBT: Button = findViewById(R.id.timerBT)

        timerBT.setOnClickListener{
            startStopTimer()
        }

        serviceIntent = Intent(applicationContext,TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))

        val story = Story( binding, this) //instiating story class

        choice1BT.setOnClickListener{
            story.selectPosition(story.nextPosition1)
        }
        choice2BT.setOnClickListener{
            story.selectPosition(story.nextPosition2)
        }
        choice3BT.setOnClickListener{
            story.selectPosition(story.nextPosition3)
        }
        choice4BT.setOnClickListener{
            story.selectPosition(story.nextPosition4)
        }

        story.startingPoint()
    }


    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent != null) {
                time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
                binding.timeTV.text = getTimeStringFromDouble(time)
            }
        }
    }


    private fun getTimeStringFromDouble(time:Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400/3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours,minutes,seconds)
    }

    private fun makeTimeString(hours: Int, minutes: Int, seconds: Int): String = String.format("%02d:%02d:%02d", hours,minutes,seconds)

    private fun startStopTimer() {
        if(timerStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA,time)
        binding.timerBT.text = "Stop Timer"
        startService(serviceIntent)
        timerStarted=true
    }

    private fun stopTimer() {
        stopService(serviceIntent)
        binding.timerBT.text = "Start Timer"
        timerStarted=false
    }


    fun goTitleScreen(){
        val titleS = Intent(this, TitleScreen::class.java)
        startActivity(titleS)
    }
}