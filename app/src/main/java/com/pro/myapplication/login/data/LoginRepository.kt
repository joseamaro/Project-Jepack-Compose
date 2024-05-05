package com.pro.myapplication.login.data

import com.pro.myapplication.login.domain.model.Login
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginRepositoryImp: LoginRepositoryImp
) {
    suspend fun doLogin(user: String, password: String) : Response<Login> {
        return loginRepositoryImp.doLogin(user, password)
    }
}