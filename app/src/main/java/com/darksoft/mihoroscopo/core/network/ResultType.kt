package com.darksoft.mihoroscopo.core.network

// La T recibira un valor de cualquier tipo
sealed class ResultType<T> {

    data class Success<T>(val data: T?) : ResultType<T>()
    // val example = ResultType.Success<HoroscopeResponse>()

    data class Error<T>(val msg: String) : ResultType<T>()
    // val example = ResultType.Error<HoroscopeResponse>("Error")

    // tambien puede entrar diferentes estados como offline

}