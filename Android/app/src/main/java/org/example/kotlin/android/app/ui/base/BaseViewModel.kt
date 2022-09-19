package org.example.kotlin.android.app.ui.base

import androidx.lifecycle.ViewModel
import org.example.kotlin.android.app.data.repository.BaseRepository
import org.example.kotlin.android.app.data.repository.UserRepository
import org.example.kotlin.android.app.data.restapi.UserApi

abstract class BaseViewModel(private val repository: BaseRepository) : ViewModel(){

    suspend fun logout(api: UserApi) = repository.logout(api)
}