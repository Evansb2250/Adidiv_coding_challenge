package com.example.a20240130_samuelbrandenburg_nycschools

sealed class NetworkResponse<T> {
    data class Success<T>(val data: T) : NetworkResponse<T>()
    data class Failed<T>(val data:Any? = null, val errorMessage: String) : NetworkResponse<T>()
}
