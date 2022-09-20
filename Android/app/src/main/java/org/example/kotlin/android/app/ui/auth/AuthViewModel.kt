package org.example.kotlin.android.app.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.kotlin.android.app.data.repository.AuthRepository
import org.example.kotlin.android.app.data.requestsBody.SignIn
import org.example.kotlin.android.app.data.responses.LoginResponse
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.ui.base.BaseViewModel

/*
    ViewModel that we will use in our fragment to communicate with the repository then that will communicate with our datasource/retrofit/backend.
    ViewModel will call the login function from repository and repository will call our login api



 */
class AuthViewModel(private val repository: AuthRepository) : BaseViewModel(repository) {
    // Muteable live data where we can put the value.
    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    //  Livedata: cannot change the live data directly, so we will access this live data outside this viewModel
    val loginResponse: LiveData<Resource<LoginResponse>>
        // is immutable (constant)
        //MutableLivedata as livedata
        get() = _loginResponse


    fun login(signin : SignIn) = viewModelScope.launch {
        delay(1000)
       _loginResponse.value = repository.signIn(signin)
    }


    suspend fun saveAuthToken(token: String?) = viewModelScope.launch {
        if (token != null) {
            repository.saveAccessToken(token)
        };
    }


   suspend fun saveUserId(userId: String?)  {
        if (userId != null) {
            repository.saveUserId(userId)
        }
    }

}