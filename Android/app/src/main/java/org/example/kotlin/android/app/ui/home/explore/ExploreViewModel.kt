package org.example.kotlin.android.app.ui.home.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.repository.ProductRepository
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseViewModel

class ExploreViewModel(private val repository: ProductRepository) : BaseViewModel(repository) {
        //private because we don't wanna expose MutableLiveData to the activity/fragment
        private val _products: MutableLiveData<Resource<List<ProductResponse>>> = MutableLiveData()

        // this can be observed from fragment/activity
        val products: LiveData<Resource<List<ProductResponse>>>
                get() = _products


        fun getAllProducts() = viewModelScope.launch {
                repository.getAllProducts().collect {
                        _products.value = it;
                }
        }


}