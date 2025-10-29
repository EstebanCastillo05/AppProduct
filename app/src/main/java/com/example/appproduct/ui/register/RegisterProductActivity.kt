package com.example.appproduct.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.appproduct.R
import com.example.appproduct.data.network.ApiClient
import com.example.appproduct.data.repository.ProductRepository
import com.example.appproduct.databinding.ActivityRegisterProductBinding

class RegisterProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterProductBinding
    private lateinit var viewModel: RegisterProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_product)

        val repository = ProductRepository(ApiClient.productApi)
        viewModel = RegisterProductViewModel(repository)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.success.observe(this) {
            if (it) {
                Toast.makeText(this, "Producto guardado", Toast.LENGTH_SHORT).show()
                // startActivity(Intent(this, ProductListActivity::class.java))
            }
        }

        viewModel.errorMessage.observe(this) { msg ->
            msg?.let { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
        }
    }
}
