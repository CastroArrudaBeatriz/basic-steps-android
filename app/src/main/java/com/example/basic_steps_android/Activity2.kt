package com.example.basic_steps_android

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_2.*
import kotlinx.android.synthetic.main.activity_main.*

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val intent = intent
        val n1 = intent.getIntExtra("number_1", 0)
        val n2 = intent.getIntExtra("number_2", 0)
        text_numbers.text = "$n1 , $n2"

        button_back.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("result", n1 + n2)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

    }
}