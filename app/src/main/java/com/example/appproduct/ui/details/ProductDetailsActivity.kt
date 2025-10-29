package com.example.appproduct.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appproduct.data.models.Product
import com.example.appproduct.databinding.ActivityProductDetailsBinding



class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getParcelableExtra<Product>("product")
        if (product != null) {
            binding.product = product
        } else {
            finish()
        }
    }
}