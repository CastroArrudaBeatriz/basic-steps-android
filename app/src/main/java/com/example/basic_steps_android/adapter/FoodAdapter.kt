package com.example.basic_steps_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.basic_steps_android.R
import com.example.basic_steps_android.model.Food
import kotlinx.android.synthetic.main.item_list.view.*

class FoodAdapter(private val context: Context,
				  private val foods: List<Food>) : BaseAdapter() {

	override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
		val food = foods[position]
		val holder: ViewMolde
		val row: View
		if (view == null) {
			row = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
			holder = ViewMolde(row)
			row.tag = holder
		} else {
			row = view
			holder = view.tag as ViewMolde
		}
		holder.foodImageView.setImageResource(R.drawable.ic_baseline_fastfood_24)
		holder.foodName.text = food.name
		holder.foodPrice.text = food.price.toString()
		return row
	}

	override fun getItem(position: Int) = foods[position]

	override fun getItemId(position: Int) = position.toLong()

	override fun getCount() = foods.size

	companion object {
		data class ViewMolde(val view: View) {
			val foodImageView: ImageView = view.imageView
			val foodName: TextView = view.food_name
			val foodPrice: TextView = view.food_price
		}
	}
}
