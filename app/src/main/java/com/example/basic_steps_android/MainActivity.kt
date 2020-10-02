package com.example.basic_steps_android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basic_steps_android.adapter.FoodAdapter
import com.example.basic_steps_android.model.Food
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val INTENT_FOOD_ID = 1
        const val SAVED_FOOD_LIST = "saved_food_list"
    }

    private val foodList = arrayListOf<Food>(
        Food("Banana", 5.0),
        Food("Biscoito", 2.50),
        Food("Arroz", 10.0)
    )

    private val foodAdapter by lazy { FoodAdapter(this, foodList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        setupFoodList()

        add_food_button.setOnClickListener {
            val intentFoodItem = Intent(this, FoodItemActivity::class.java)
            startActivityForResult(intentFoodItem, INTENT_FOOD_ID)
        }
    }

    private fun setupFoodList() {
        listview_food.setOnItemClickListener { _, _, position, id ->
            val (name, price) = foodList[position]
            Toast.makeText(this, "click: $name $price", Toast.LENGTH_SHORT).show()
        }
        listview_food.adapter = foodAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == INTENT_FOOD_ID && resultCode == Activity.RESULT_OK){
            if(data != null){
                insertFood(data.getStringExtra("name"), data.getDoubleExtra("price",0.0))
            }
        }
    }

    private fun insertFood(name: String, price: Double) {
        foodList.add(Food(name, price))
        foodAdapter.notifyDataSetChanged()
    }

    override fun onPause() {super.onPause()}

    override fun onResume() {super.onResume()}

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(SAVED_FOOD_LIST, foodList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        foodList.clear()
        savedInstanceState.getParcelableArrayList<Food>(SAVED_FOOD_LIST)?.let { foodList.addAll(it) }
    }


}
