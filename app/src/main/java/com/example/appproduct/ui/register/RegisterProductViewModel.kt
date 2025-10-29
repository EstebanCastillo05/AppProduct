package com.example.appproduct.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.example.appproduct.data.models.Product
import com.example.appproduct.data.repository.ProductRepository
import kotlinx.coroutines.launch

class RegisterProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun saveProduct() {
        val productName = name.value ?: ""
        val productPrice = price.value?.toDoubleOrNull() ?: 0.0
        val productDesc = description.value ?: ""

        val product = Product(
            id = 0,
            name = productName,
            price = productPrice,
            description = productDesc
        )

        viewModelScope.launch {
            try {
                val response = repository.saveProduct(product)
                if (response.isSuccessful) {
                    success.postValue(true)
                } else {
                    val errorBody = response.errorBody()?.string()
                    errorMessage.postValue("Error ${response.code()}: $errorBody")
                }
            } catch (e: Exception) {
                errorMessage.postValue("Exception: ${e.localizedMessage}")
            }
        }
    }
}
