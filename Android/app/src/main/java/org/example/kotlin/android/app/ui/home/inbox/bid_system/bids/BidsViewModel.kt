package org.example.kotlin.android.app.ui.home.inbox.bid_system.bids

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.repository.BidRepository
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseViewModel

class BidsViewModel(private val repository: BidRepository) : BaseViewModel(repository) {
    private val _ownersProducts: MutableLiveData<Resource<List<ProductResponse>>> = MutableLiveData()

    val ownersProducts: LiveData<Resource<List<ProductResponse>>> get() = _ownersProducts

    //TODO only fetch products with a bid
    fun getAllProductsByUserId(userId: String) {
        viewModelScope.launch { repository.getOwnersProductById(userId) }
    }

}