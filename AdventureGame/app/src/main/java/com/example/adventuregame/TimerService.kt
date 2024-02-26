package com.example.adventuregame

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*

class TimerService: Service() {

    private val timer = Timer()

    companion object{
        const val TIMER_UPDATED = "timerUpdated"
        const val TIME_EXTRA = "timeExtra"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time = intent?.getDoubleExtra(TIME_EXTRA,0.0)
        return START_NOT_STICKY
    }

    private inner class TimeTask(private var time:Double): TimerTask() {
        override fun run() {
            val intent  = Intent(TIMER_UPDATED)
            time++
            intent.putExtra(TIME_EXTRA,time)
            sendBroadcast(intent)
        }

    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}