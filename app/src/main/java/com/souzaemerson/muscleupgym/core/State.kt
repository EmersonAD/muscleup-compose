package com.souzaemerson.muscleupgym.core

data class State<out T>(
    val status: Status = Status.Loading,
    val data: T? = null,
    val loading: Boolean = false,
    val error: Throwable? = null
) {
    companion object {
        fun loading() = State(Status.Loading, null, loading = true, error = null)
        fun <T> success(data: T) = State(Status.Success, data, loading = false, error = null)
        fun error(error: Throwable) = State(Status.Error, null, loading = false, error = error)
    }
}

enum class Status {
    Success, Loading, Error
}