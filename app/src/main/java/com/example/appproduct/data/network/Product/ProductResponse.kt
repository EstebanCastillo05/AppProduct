package com.example.appproduct.data.network.Product

import com.example.appproduct.data.models.Product

data class ProductResponse(
    val success: Boolean,
    val message: String,
    val product: Product? = null
)