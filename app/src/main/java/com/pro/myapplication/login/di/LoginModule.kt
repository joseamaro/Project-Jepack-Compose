package com.pro.myapplication.login.di

import com.pro.myapplication.base.ApiServiceFactory
import com.pro.myapplication.login.data.remote.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Singleton
    @Provides
    fun provider(): LoginApi{
        return ApiServiceFactory.build(LoginApi::class.java, "https://run.mocky.io/")
    }
}