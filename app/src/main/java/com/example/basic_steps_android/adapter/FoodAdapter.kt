package com.example.basic_steps_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basic_steps_android.R
import com.example.basic_steps_android.model.Food
import kotlinx.android.synthetic.main.item_list.view.*
import kotlin.reflect.KFunction2

class FoodAdapter(private val context: Context,
                  private val foods: List<Food>,
                  private val callback: KFunction2<Food, Int, Unit>
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>(){

    //view holder make struct to item
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val foodImageView: ImageView = view.imageView
        val foodName: TextView = view.food_name
        val foodPrice: TextView = view.food_price
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutReference = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        val viewHolder = ViewHolder(layoutReference)

        viewHolder.itemView.setOnClickListener {
            val food = foods[viewHolder.adapterPosition]
            callback(food , foods.indexOf(food))
        }
        return viewHolder
    }

    override fun getItemCount(): Int = foods.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val(name, price) = foods[position]
        holder.foodImageView.setImageResource(R.drawable.ic_baseline_fastfood_24)
        holder.foodName.text = name
        holder.foodPrice.text = price.toString()
    }
}

