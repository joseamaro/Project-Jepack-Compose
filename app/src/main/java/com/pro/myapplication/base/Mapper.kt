package com.pro.myapplication.base

fun interface Mapper<in E, out T> {
    fun map(params: E): T
}