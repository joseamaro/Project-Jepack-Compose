package com.pro.myapplication.login.domain.model

data class Login(
    val success: String,
    val data: UserInfo,
    val error: Boolean
)
