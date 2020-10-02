package com.example.basic_steps_android.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(val name: String, var price: Double) : Parcelable