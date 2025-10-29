package com.example.appproduct.data.network

import com.example.appproduct.data.models.Product
import com.example.appproduct.data.network.Product.ProductRequest
import com.example.appproduct.data.network.Product.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("products/registrar")
    suspend fun saveProduct(@Body request: ProductRequest): Response<ProductResponse>

    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
}
