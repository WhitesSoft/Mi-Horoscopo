package com.darksoft.mihoroscopo.data

import com.darksoft.mihoroscopo.core.network.ErrorType
import com.darksoft.mihoroscopo.core.network.ErrorType.*
import com.darksoft.mihoroscopo.core.network.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException


open class BaseRepository {

    // INLINE lo que hace es replicar el codigo de la funcion en donde se llama
    // Crossinline lo que hace es que prepara la funcion lamba para la funcion inline, dejarle preparado
    // el codigo para que se ejecute en la funcion inline

    inline fun <T> runApiCallFlow(crossinline call:() -> Response<T>): Flow<ResultType<T>> = flow {
        try {
            val response = call()
            if (response.isSuccessful){
                emit(ResultType.Success(response.body()))
            } else {
                val error = when (response.code()) {
                    BadRequest.errorCode -> BadRequest
                    Unauthorized.errorCode -> Unauthorized
                    Forbidden.errorCode -> Forbidden
                    NotFound.errorCode -> NotFound
                    InternalServerError.errorCode -> InternalServerError
                    else -> UncontrolledError(response.code())
                }
                emit(ResultType.Error(error))
            }
        } catch (e: Exception) {
            emit(ResultType.Error(ExceptionError(e)))
        }
    }

}