package com.haz7.guesstheanswer

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import kotlin.random.Random

class HardActivity : AppCompatActivity() {



    lateinit var question: TextView
    lateinit var trueBtn: Button
    lateinit var falseBtn: Button
    lateinit var refresh: ImageButton
    lateinit var shareQuestion: ImageButton
    lateinit var score:TextView
    lateinit var life:TextView





    val QuestionsList: ArrayList<String> = arrayListOf(
    )
    val Answers: ArrayList<Boolean> = arrayListOf(

    )

    val AnswersDetails: ArrayList<String> = arrayListOf(
    )
    lateinit var CurrentQuestion:String
    lateinit var CurrentAnswerDetails:String
    var CurreentAnswer:Boolean = false
    var scoreCount=0
    var lifeCount = 2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        question = findViewById(R.id.textViewQuestion)
        trueBtn = findViewById(R.id.btnStart)
        falseBtn = findViewById(R.id.btnFalse)
        refresh = findViewById(R.id.btnChangeQuastion)
        shareQuestion = findViewById(R.id.btnSHare)
        score = findViewById(R.id.scoreNumber)
        life = findViewById(R.id.lifeTxtviw)


        showQuestion()

        refresh.setOnClickListener{
            showQuestion()
            scoreCount = scoreCount-5
            score.text= scoreCount.toString()
        }

        trueBtn.setOnClickListener {
            onTrueClicked()
        }
        falseBtn.setOnClickListener {
            onFalseClick()
        }
        shareQuestion.setOnClickListener {
            onShareQuestionClicked()
        }

    }


    private fun showQuestion() {
        var json:String?=null
        try {
            var input: InputStream = assets.open("questions_new.json")
            json = input.bufferedReader().use { it.readText() }
            var jasonArray= JSONArray(json)
            for (i in 0 until jasonArray.length()){
                var jsonoj = jasonArray.getJSONObject(i)
                QuestionsList.add(jsonoj.getString("question"))
                Answers.add(jsonoj.getBoolean("A"))
                AnswersDetails.add(jsonoj.getString("A_D"))


            }
        }catch (e: IOException){

        }




        for (i in 0..QuestionsList.size){
            var index = Random.nextInt(QuestionsList.size)


            CurrentQuestion = QuestionsList[index]
            CurreentAnswer = Answers[index]
            CurrentAnswerDetails = AnswersDetails[index]
            question.setText(CurrentQuestion)
        }




    }
    private fun onTrueClicked(){

        if (CurreentAnswer == true){
            Toast.makeText(this,"Right answer",Toast.LENGTH_SHORT).show()
            scoreCount = scoreCount+10
            score.text= scoreCount.toString()

            showQuestion()
            winner()

        }else{
            Toast.makeText(this,"Wrong answer",Toast.LENGTH_SHORT).show()
            scoreCount = scoreCount-10
            score.text= scoreCount.toString()
            lifeCount -= 1
            life.text=lifeCount.toString()
            val intent = Intent(this@HardActivity, AnswerActivity::class.java)
            intent.putExtra("QuestionAnswer", CurrentAnswerDetails)
            startActivity(intent)

            showQuestion()
            winner()
            tryAgain()
        }


    }
    private fun onFalseClick(){
        if (CurreentAnswer == false){
            Toast.makeText(this,"Right answer",Toast.LENGTH_SHORT).show()
            scoreCount = scoreCount+10
            score.text= scoreCount.toString()
            showQuestion()
            winner()

        }else{
            Toast.makeText(this,"Wrong answer",Toast.LENGTH_SHORT).show()
            scoreCount = scoreCount-10
            score.text= scoreCount.toString()
            lifeCount -= 1
            life.text=lifeCount.toString()
            val intent = Intent(this@HardActivity, AnswerActivity::class.java)
            intent.putExtra("QuestionAnswer", CurrentAnswerDetails)
            startActivity(intent)

            showQuestion()
            winner()
            tryAgain()
        }

    }
    private fun onShareQuestionClicked() {
        scoreCount = scoreCount-15
        score.text= scoreCount.toString()
        val intent = Intent(this@HardActivity, ShareActivity::class.java)
        intent.putExtra("Question text extra", CurrentQuestion)
        startActivity(intent)
    }
    fun winner(){
        if(scoreCount>=50){
            var alertDialog=AlertDialog.Builder(this@HardActivity)
            alertDialog.setTitle("YOU ARE SMART AND WINNER :)").setMessage("Do you want to play again?")
                .setIcon(R.drawable.ic_win_done_24)
                . setCancelable(false)
                .setNegativeButton("NO", DialogInterface.OnClickListener { dialogInterface, which ->
                    dialogInterface.cancel()
                    finish()
                })
                .setPositiveButton("YES", DialogInterface.OnClickListener { dialogInterface, which ->
                    scoreCount=0
                    score.text= scoreCount.toString()
                })
                alertDialog.create().show()

        }

    }
    fun tryAgain() {
        if (lifeCount==0){
            var alertDialog=AlertDialog.Builder(this@HardActivity)
            alertDialog.setTitle("SORRY, GOOD LUCK NEXT TIME :(").setMessage("Do you want to try again?")
                .setIcon(R.drawable.ic_win_done_24)
                . setCancelable(false)
                .setNegativeButton("NO", DialogInterface.OnClickListener { dialogInterface, which ->
                    dialogInterface.cancel()
                    finish()
                })
                .setPositiveButton("YES", DialogInterface.OnClickListener { dialogInterface, which ->
                    scoreCount=0
                    score.text= scoreCount.toString()
                    lifeCount=3
                    life.text=lifeCount.toString()
                })
            alertDialog.create().show()

        }
    }


}