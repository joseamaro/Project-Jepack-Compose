package com.pro.myapplication.login.data.remote

import com.pro.myapplication.login.data.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginApi {
    @GET("v3/e9bf03e4-41d7-4045-9fb5-086d146b78ea")
    suspend fun doLogin() : Response<LoginResponse>
}