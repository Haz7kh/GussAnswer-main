package com.haz7.guesstheanswer

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Levels : AppCompatActivity() {
    lateinit var welcome:TextView
    lateinit var BtnEasy:Button
    lateinit var BtnHard:Button

    //lateinit var image:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)
        //animation
        val ttb=AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)
        val stb=AnimationUtils.loadAnimation(this,R.anim.scale_to_big)


        welcome=findViewById(R.id.welcomeText)
        BtnEasy=findViewById(R.id.BtnEasy)
        BtnHard=findViewById(R.id.BtnHard)
        //image=findViewById(R.id.imageView)

        //set the anim
        welcome.startAnimation(ttb)

        //image.startAnimation(stb)

        BtnEasy.setOnClickListener {
            val intent = Intent(this@Levels, QuestionActivity::class.java)
            startActivity(intent)
        }

        BtnHard.setOnClickListener {
            val intent = Intent(this@Levels, HardActivity::class.java)
            startActivity(intent)
        }

    }
}