package org.example.kotlin.android.app.ui.home.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.repository.ProductRepository
import org.example.kotlin.android.app.data.repository.UserRepository
import org.example.kotlin.android.app.data.responses.ProductResponse
import org.example.kotlin.android.app.data.responses.UserResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseViewModel

class ProfileViewModel(
    private val userRepository: UserRepository,
    ) : BaseViewModel(userRepository) {

    //private because we don't wanna expose MutableLiveData to the activity/fragment
    private val _products: MutableLiveData<Resource<List<ProductResponse>>> = MutableLiveData()

    // this can be observed from fragment/activity
    val products: LiveData<Resource<List<ProductResponse>>>
        get() = _products


    private val _user: MutableLiveData<Resource<UserResponse>> = MutableLiveData();
    val user: LiveData<Resource<UserResponse>>
        get() = _user


    fun getUser(userId: String) = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = userRepository.getUser(userId);
    }

    fun getUserProductsById(userId: String) = viewModelScope.launch {
        _products.value = userRepository.getUserProductsById(userId);
    }


}