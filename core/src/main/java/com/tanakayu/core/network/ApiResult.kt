package com.tanakayu.core.network

sealed class ApiResult<out T : Any> {

    class Success<out T : Any>(val data: T) : ApiResult<T>()

    class Error(val code: Int = 400) : ApiResult<Nothing>()
}