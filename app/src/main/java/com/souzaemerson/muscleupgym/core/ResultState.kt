package com.souzaemerson.muscleupgym.core

data class ResultState<out T>(
    val status: Status,
    val data: T?,
    val loading: Boolean,
    val error: Throwable?
) {
    companion object {
        fun <T> success(data: T) = ResultState(Status.Success, data, false, null)
        fun loading() = ResultState(Status.Loading, null, true, null)
        fun error(throwable: Throwable) = ResultState(Status.Error, null, false, throwable)
    }
}

enum class Status {
    Success, Loading, Error
}
