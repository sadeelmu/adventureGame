package com.example.adventuregame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TitleScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_screen)

        val startBT: Button = findViewById(R.id.startBT)

        startBT.setOnClickListener{

            val Intent = Intent(this, GameScreen::class.java)
            startActivity(Intent)
        }
    }
}