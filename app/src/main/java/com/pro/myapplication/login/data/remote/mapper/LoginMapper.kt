package com.pro.myapplication.login.data.remote.mapper

import com.pro.myapplication.base.Mapper
import com.pro.myapplication.login.data.remote.response.LoginResponse
import com.pro.myapplication.login.domain.model.Login
import javax.inject.Inject

class LoginMapper @Inject constructor(
    private val userInfoMapper: UserInfoMapper
) : Mapper<LoginResponse, Login> {

    override fun map(params: LoginResponse): Login {
        return Login(
            success = params.success,
            data = userInfoMapper.map(params.data),
            error = params.error
        )
    }
}