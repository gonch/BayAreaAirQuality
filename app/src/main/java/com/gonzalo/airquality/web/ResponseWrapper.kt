package com.gonzalo.airquality.web

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val value: T): ResponseWrapper<T>()
    data class Error(val errorMessage: String): ResponseWrapper<Nothing>()
}