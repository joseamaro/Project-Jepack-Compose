package com.pro.myapplication.login.data.remote.mapper

import com.pro.myapplication.base.Mapper
import com.pro.myapplication.login.data.remote.response.UserResponse
import com.pro.myapplication.login.domain.model.UserInfo
import javax.inject.Inject

class UserInfoMapper @Inject constructor(): Mapper<UserResponse, UserInfo> {
    override fun map(params: UserResponse): UserInfo {
       return UserInfo(
           name = params.nombre,
           lastName = params.apellido,
           age = params.edad
       )
    }
}