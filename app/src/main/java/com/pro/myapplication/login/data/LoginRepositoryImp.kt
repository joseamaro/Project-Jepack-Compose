package com.pro.myapplication.login.data

import com.pro.myapplication.login.data.remote.LoginApi
import com.pro.myapplication.login.data.remote.mapper.LoginMapper
import com.pro.myapplication.login.domain.model.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val loginApi: LoginApi,
    private val loginMapper: LoginMapper
) {

    suspend fun doLogin(user: String, password: String): Response<Login> {
        return withContext(Dispatchers.IO) {
            try {
                val response = loginApi.doLogin()
                Response.success(loginMapper.map(response.body()!!))
            } catch (e: Exception) {
                Response.error(
                    e.hashCode(),
                    "Error en la autenticación: ${e.message}"
                        .toResponseBody("".toMediaTypeOrNull())
                )
            }
        }
    }
}