package org.example.kotlin.android.app.data.restapi

import okhttp3.ResponseBody

/*
Sealed classes
- represent a limited set of possibilities ex: A web request either succeeds or fails.
 */



// wrap the api response. why? -> bc API sometimes returns actual information
// and it can also return error. So we need to handle both scenarios.
sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()

}