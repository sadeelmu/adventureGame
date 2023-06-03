package com.example.adventuregame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import com.example.adventuregame.databinding.ActivityGameScreenBinding

class GameScreen : AppCompatActivity() {

    private lateinit var binding: ActivityGameScreenBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameTV: TextView = findViewById(R.id.gameTV)
        val gameIV: ImageView = findViewById(R.id.gameIV)
        val choice1BT: Button = findViewById(R.id.choice1BT)
        val choice2BT: Button = findViewById(R.id.choice2BT)
        val choice3BT: Button = findViewById(R.id.choice3BT)
        val choice4BT: Button = findViewById(R.id.choice4BT)

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

    fun goTitleScreen(){
        val titleS = Intent(this, TitleScreen::class.java)
        startActivity(titleS)
    }
}