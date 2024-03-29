package com.example.adventuregame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.ToggleButton

class TitleScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_screen)

        val startBT: Button = findViewById(R.id.startBT)

        startBT.setOnClickListener{

            val musicIntent = Intent(this, GameScreen::class.java)
            startActivity(musicIntent)

        }

        val musicToggle : ToggleButton = findViewById<View>(R.id.musicBT) as ToggleButton
        musicToggle.setOnCheckedChangeListener{buttonView, isChecked->
            if (isChecked){
                startService(Intent(this, MusicService::class.java))
                Toast.makeText(this, "start music", Toast.LENGTH_SHORT).show()
            } else{
                stopService(Intent(this, MusicService::class.java))
            }
        }
    }
}