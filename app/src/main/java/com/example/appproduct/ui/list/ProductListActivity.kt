package com.example.appproduct.ui.list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appproduct.data.models.Product
import com.example.appproduct.data.network.ApiClient
import com.example.appproduct.data.repository.ProductRepository
import com.example.appproduct.databinding.ActivityProductListBinding
import com.example.appproduct.ui.detail.ProductDetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding
    private lateinit var adapter: ProductAdapter
    private val repository by lazy { ProductRepository(ApiClient.productApi) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ProductAdapter { product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", product)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        fetchProducts()
    }

    private fun fetchProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getProducts()
                if (response.isSuccessful) {
                    val list = response.body() ?: emptyList()
                    withContext(Dispatchers.Main) {
                        adapter.submitList(list)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ProductListActivity, "Error al obtener productos", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ProductListActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
