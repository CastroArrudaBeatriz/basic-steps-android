package com.example.basic_steps_android

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_SECOND_ACTIVITY = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_open.setOnClickListener {
            val intent = Intent(this,Activity2::class.java)
            intent.putExtra("number_1", if(isValidString(number_1.text.toString())) number_1.text.toString().toInt() else 0 )
            intent.putExtra("number_2", if(isValidString(number_2.text.toString())) number_2.text.toString().toInt() else 0 )
            startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SECOND_ACTIVITY && resultCode == Activity.RESULT_OK){
            if(data != null){
                label_result.text = getString(R.string.result_label) + "  " +  data.getIntExtra("result",0)
            }
        }
    }

    private fun isValidString(value: String): Boolean = !value.isNullOrEmpty()

}
