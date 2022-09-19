package org.example.kotlin.android.app.ui.home.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.repository.UserRepository
import org.example.kotlin.android.app.data.responses.UserResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseViewModel

class ProfileViewModel(private val repository: UserRepository) : BaseViewModel(repository) {
    private val _user: MutableLiveData<Resource<UserResponse>> = MutableLiveData();
    val user: LiveData<Resource<UserResponse>>
        get() = _user


    fun getUser(userId: String) = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser(userId);
    }
}