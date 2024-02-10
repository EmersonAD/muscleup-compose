package com.souzaemerson.muscleupgym.core

sealed class Resource<T> {
    data class Loading<T>(val boolean: Boolean) : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val throwable: Throwable) : Resource<T>()
}