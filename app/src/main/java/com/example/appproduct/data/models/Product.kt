package com.example.appproduct.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int? = null,
    val name: String,
    val price: Double,
    val description: String
) : Parcelable
