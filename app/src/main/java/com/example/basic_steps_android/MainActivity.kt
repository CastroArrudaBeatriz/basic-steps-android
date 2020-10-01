package com.example.basic_steps_android

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val SECOND_ACTIVITY = "android.intent.action.SECOND_ACTIVITY"
        const val TEXT_COMMONS = "android.intent.category.TEXT_COMMONS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        button_open.setOnClickListener {

            //chamar explicita passando a classe
            /*val intent = Intent(this, Activity2::class.java)
            startActivity(intent)*/

            //chamar com intent implicita
            val secAtivityIntent = Intent()
            secAtivityIntent.action = SECOND_ACTIVITY
            secAtivityIntent.addCategory(TEXT_COMMONS)
            if(secAtivityIntent.resolveActivity(packageManager) != null) startActivity(secAtivityIntent)

        }

        button_add_event.setOnClickListener {
            val cal: Calendar = Calendar.Builder().setCalendarType("iso8601")
                .setDate(2020,9,7).build()
            addEvent("Aniversario da Bia", "fique aí na sua casa mesmo", cal.timeInMillis , cal.timeInMillis)
        }
    }


    private fun addEvent(title: String, location: String, begin: Long, end: Long) {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.Events.EVENT_LOCATION, location)
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}
