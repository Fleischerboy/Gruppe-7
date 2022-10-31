package org.example.kotlin.android.app.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.kotlin.android.app.data.restapi.Resource
import org.example.kotlin.android.app.data.restapi.UserApi
import retrofit2.HttpException

/*
Will be base repository for all our repository classes
we will inherit(arve) this class til our actual concrete repository classes.

 */
abstract class BaseRepository {

    // function for safely call the api
    suspend fun <T> safeApiCall(apiCall: suspend() -> T) : Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            }
            catch (throwable: Throwable) {
               when(throwable) {
                   is HttpException -> {
                       Resource.Failure(false, throwable.code(), throwable.response()?.errorBody());
                   }
                   else -> {
                       Resource.Failure(true, null, null);
                   }
               }
            }
        }
    }


    suspend fun logout(api: UserApi) = safeApiCall {
        api.logout()
    }

}