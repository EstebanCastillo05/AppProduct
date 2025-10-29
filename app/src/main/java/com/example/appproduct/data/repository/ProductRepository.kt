package com.example.appproduct.data.repository

import com.example.appproduct.data.models.Product
import com.example.appproduct.data.network.ApiService
import com.example.appproduct.data.network.Product.ProductRequest
import com.example.appproduct.data.network.Product.ProductResponse
import retrofit2.Response

class ProductRepository(private val api: ApiService) {

    suspend fun getProducts(): Response<List<Product>> {
        return api.getProducts()
    }

    suspend fun saveProduct(product: Product): Response<ProductResponse> {
        val request = ProductRequest(
            name = product.name,
            price = product.price,
            description = product.description
        )
        return api.saveProduct(request)
    }
}
