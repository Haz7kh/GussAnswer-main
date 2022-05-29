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

    lateinit var BtnEasy:Button
    lateinit var BtnHard:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)




        BtnEasy=findViewById(R.id.BtnEasy)
        BtnHard=findViewById(R.id.BtnHard)




        BtnEasy.setOnClickListener {
            val intente = Intent(this@Levels, QuestionActivity::class.java)
            startActivity(intente)

        }

        BtnHard.setOnClickListener {
            val intent = Intent(this@Levels, HardActivity::class.java)
            startActivity(intent)
        }

    }
}