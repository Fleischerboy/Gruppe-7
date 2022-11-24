package org.example.kotlin.android.app.data.restapi

import org.example.kotlin.android.app.data.requestsBody.SignIn
import org.example.kotlin.android.app.data.requestsBody.SignUp
import org.example.kotlin.android.app.data.responses.LoginResponse
import org.example.kotlin.android.app.data.responses.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    /*
    Coroutines:
    1. You can start many coroutine inside a single thread.
    2. Coroutine are suspendable, which means that we can execute some instructions, pause the coroutine in the middle of execution
     and let it continue when we wanted to and that is something threads cannot do.
    3. they can switch their context
    4. Lightweight threads with some useful extra functionality.
     */
    // suspend bc we need to call it async
    // suspend bc we are going to use coroutines for out asynchronous calls
    // to call this function we need a coroutine


    @POST("signup")
    suspend fun signUp(@Body userSignUpData: SignUp): SignUpResponse





    @POST("signin")
    suspend fun signIn(@Body userSignInData: SignIn): LoginResponse

}