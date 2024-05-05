package com.pro.myapplication.login.domain.usecase

import com.pro.myapplication.login.data.LoginRepository
import com.pro.myapplication.login.domain.model.Login
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    suspend operator fun invoke(user: String, password: String) : Response<Login>{
        return repository.doLogin(user, password)
    }
}