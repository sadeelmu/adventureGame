package com.example.adventuregame

import android.icu.text.Transliterator.Position
import android.view.View
import android.widget.TextView
import com.example.adventuregame.databinding.ActivityGameScreenBinding

class Story(private val binding: ActivityGameScreenBinding, val gs: GameScreen) {
    //class constructor

    //adding an instance variable for the database handler
    private val dbHandler: DatabaseHandler = DatabaseHandler(gs.applicationContext)

    var nextPosition1 = " "
    var nextPosition2 = " "
    var nextPosition3 = " "
    var nextPosition4 = " "

    var masterSword = false
    var killedPlant = false

    fun selectPosition(position: String){
        when(position){
            "startingPoint" -> startingPoint()
            "sign" -> sign()
            "monster" -> monster()
            "sword" -> sword()
            "pipe" -> pipe()
            "plant" -> plant()
            "dead" -> dead()
            "goTitleScreen" -> gs.goTitleScreen()
            "attack" -> attack()
        }
        if(position == "goTitleScreen"){
           dbHandler.deleteAllDecisions()
        }
        else if (position == "sign"){
            dbHandler.deleteLastDecision()
        }
        else {
           dbHandler.addDecision(position)
        }
    }

    fun showAllButtons(){
        binding.choice1BT.setVisibility(View.VISIBLE)
        binding.choice2BT.setVisibility(View.VISIBLE)
        binding.choice3BT.setVisibility(View.VISIBLE)
        binding.choice4BT.setVisibility(View.VISIBLE)
    }
    fun startingPoint(){
        //this method describes the first location where the game starts
        //display the opening narrative text, oppening image, and so on

        binding.gameTV.setText("You are on the road. There is a wooden sign nearby. \n \n What will you do?")

        binding.gameIV.setImageResource(R.drawable.trail)
        showAllButtons()

        binding.choice1BT.setText("Go north")
        binding.choice2BT.setText("Go south")
        binding.choice3BT.setText("Go west")
        binding.choice4BT.setText("Read the sign")

        nextPosition1 = "monster"
        nextPosition2 = "sword"
        nextPosition3 = "pipe"
        nextPosition4 = "sign"

    }

    fun sign(){
        binding.gameTV.setText("The sign says: \n \n  Monster Ahead Do Not Go")
        binding.gameIV.setImageResource(R.drawable.wooden_sign)

        binding.choice1BT.setText("Go back")
        binding.choice2BT.setVisibility(View.INVISIBLE)
        binding.choice3BT.setVisibility(View.INVISIBLE)
        binding.choice4BT.setVisibility(View.INVISIBLE)

        nextPosition1 = "startingPoint"

    }

    fun monster(){
        //to defeat this monster you must satisfy two conditions
        //defeat the plant and have the master sword
        binding.gameIV.setImageResource(R.drawable.gargoyle)
        binding.gameTV.setText("You encounter a gargoyle!!!\n What will you do?")

        binding.choice1BT.setText("Attack")
        binding.choice2BT.setText("Run")
        binding.choice3BT.setVisibility(View.INVISIBLE)
        binding.choice4BT.setVisibility(View.INVISIBLE)
        nextPosition1 = "attack"
        nextPosition2 = "startingPoint"


    }
    fun attack(){

        if(masterSword==true && killedPlant==true){
            binding.gameIV.setImageResource(R.drawable.open_treasure_chest)
            binding.gameTV.setText("With your legendary master sword and experience as a warrior, the monster has no chance to win!\nYou get the treasure and the world is safe" +
                    "\n\nTHE END")
            binding.choice1BT.setText("Go to the title screen")
            binding.choice2BT.setVisibility(View.INVISIBLE)
            binding.choice3BT.setVisibility(View.INVISIBLE)
            binding.choice4BT.setVisibility(View.INVISIBLE)
            nextPosition1 = "goTitleScreen"
        }
        else{
            binding.gameIV.setImageResource(R.drawable.hasty_grave)
            binding.gameTV.setText("You fight well but the monster killed you!! \n\n GAME OVER")
            binding.choice1BT.setText("You have died! :(")
            binding.choice2BT.setVisibility(View.INVISIBLE)
            binding.choice3BT.setVisibility(View.INVISIBLE)
            binding.choice4BT.setVisibility(View.INVISIBLE)
            nextPosition1 = "dead"
        }

    }

    fun sword(){
        binding.gameTV.setText("Great you found a master sword! \nYou can defeat the monster now!")
        binding.gameIV.setImageResource(R.drawable.sword_altar)

        masterSword = true

        binding.choice1BT.setText("Go back")
        binding.choice2BT.setVisibility(View.INVISIBLE)
        binding.choice3BT.setVisibility(View.INVISIBLE)
        binding.choice4BT.setVisibility(View.INVISIBLE)

        nextPosition1 = "startingPoint"
    }

    fun pipe(){
        binding.gameTV.setText("You find a ginatic pipe")
        binding.gameIV.setImageResource(R.drawable.warp_pipe)

        binding.choice1BT.setText("Look Inside")
        binding.choice2BT.setText("Go back")
        binding.choice3BT.setVisibility(View.INVISIBLE)
        binding.choice4BT.setVisibility(View.INVISIBLE)

        nextPosition1 = "plant"
        nextPosition2 = "startingPoint"
    }

    fun plant(){
        binding.gameIV.setImageResource(R.drawable.carnivorous_plant)

        if(masterSword==false){
            binding.gameTV.setText("Piranha Plant is inside!!! \nAlas you are eaten")

            binding.choice1BT.setText("You died! :(")
            binding.choice2BT.setVisibility(View.INVISIBLE)
            binding.choice3BT.setVisibility(View.INVISIBLE)
            binding.choice4BT.setVisibility(View.INVISIBLE)
            nextPosition1 = "dead"
        }
        else if(masterSword==true){
            binding.gameTV.setText("You have defeated the piranha Plant with your master sword! \nYou will feel much stronger now!!!")

            killedPlant=true

            binding.choice1BT.setText("Go back")
            binding.choice2BT.setVisibility(View.INVISIBLE)
            binding.choice3BT.setVisibility(View.INVISIBLE)
            binding.choice4BT.setVisibility(View.INVISIBLE)

            nextPosition1 = "startingPoint"
        }
    }

    fun dead(){
        binding.gameTV.setText("You are dead.\n\nGAME OVER")
        binding.gameIV.setImageResource(R.drawable.hasty_grave)
        binding.choice1BT.setText("Go to the title screen")
        binding.choice2BT.setVisibility(View.INVISIBLE)
        binding.choice3BT.setVisibility(View.INVISIBLE)
        binding.choice4BT.setVisibility(View.INVISIBLE)

        nextPosition1 = "goTitleScreen"
        
    }

}
