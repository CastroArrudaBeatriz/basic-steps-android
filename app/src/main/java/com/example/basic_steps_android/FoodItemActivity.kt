package com.example.basic_steps_android

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basic_steps_android.model.Food
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_item_activity.*
import kotlinx.android.synthetic.main.item_list.*

class FoodItemActivity : AppCompatActivity() {

    companion object {
        const val FOOD_NAME = "name"
        const val FOOD_PRICE = "price"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_item_activity)
    }

    override fun onStart() {
        super.onStart()

        add_food_item_button.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(FOOD_NAME, if(!edit_food_name.text.toString().isNullOrEmpty()) edit_food_name.text.toString() else "")
            resultIntent.putExtra(FOOD_PRICE, if(!edit_food_price.text.toString().isNullOrEmpty()) edit_food_price.text.toString().toDouble() else 0.0)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}