package com.pro.myapplication.login.data.remote.response

data class LoginResponse(
    val success: String,
    val data: UserResponse,
    val error: Boolean
)