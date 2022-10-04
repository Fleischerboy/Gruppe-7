package org.example.kotlin.android.app.ui.home.sell

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.repository.BaseRepository
import org.example.kotlin.android.app.data.repository.SellRepository
import org.example.kotlin.android.app.data.requestsBody.SellProduct
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseViewModel

class SellViewModel(private val repository: SellRepository) : BaseViewModel(repository) {

    private val _productResponse: MutableLiveData<Resource<ProductResponse>> = MutableLiveData();

    val productResponse: LiveData<Resource<ProductResponse>>
        get() = _productResponse

    fun sellProduct(userId : String,sellProduct: SellProduct) = viewModelScope.launch {
        _productResponse.value = repository.sellProduct(userId,sellProduct);
    }

}