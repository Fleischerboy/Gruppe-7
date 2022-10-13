package org.example.kotlin.android.app.ui.home.productOverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.repository.ProductRepository
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseViewModel

class ProductViewModel(private val repository: ProductRepository) : BaseViewModel(repository) {
    //private because we don't wanna expose MutableLiveData to the activity/fragment
    private val _product: MutableLiveData<Resource<ProductResponse>> = MutableLiveData()

    // this can be observed from fragment/activity
    val product: LiveData<Resource<ProductResponse>>
        get() = _product



    fun getProduct(productId: Int) = viewModelScope.launch {
        _product.value = repository.getProductById(productId);
    }

}
