package org.example.kotlin.android.app.ui.home.inbox.bid_system.bid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.repository.BidRepository
import org.example.kotlin.android.app.data.responses.BidResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseViewModel


class BidViewModel(private val repository: BidRepository) : BaseViewModel(repository) {
    private val _bids: MutableLiveData<Resource<List<BidResponse>>> = MutableLiveData()

    val bids: LiveData<Resource<List<BidResponse>>> get() = _bids

    fun getBidsOnProduct(productId: String) = viewModelScope.launch {
        _bids.value = repository.getBidsOnProduct(productId)
    }


}