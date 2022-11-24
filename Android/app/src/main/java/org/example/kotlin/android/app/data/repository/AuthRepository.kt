package org.example.kotlin.android.app.data.repository

import org.example.kotlin.android.app.data.UserPreferences
import org.example.kotlin.android.app.data.requestsBody.SignIn
import org.example.kotlin.android.app.data.requestsBody.SignUp
import org.example.kotlin.android.app.data.restapi.AuthApi

/*
repository communicates with our backend api or our local storage.
 */

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun signIn(userSignInData: SignIn) = safeApiCall {
        api.signIn(userSignInData);
    }

    suspend fun signUp(userSignUpData: SignUp) = safeApiCall {
        api.signUp(userSignUpData);
    }


    suspend fun saveAccessToken(token: String) {
        preferences.saveAccessToken(token);
    }

    suspend fun saveUserId(userId: String) {
        preferences.saveUserId(userId);
    }






}