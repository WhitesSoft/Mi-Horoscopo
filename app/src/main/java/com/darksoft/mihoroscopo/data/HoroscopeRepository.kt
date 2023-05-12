package com.darksoft.mihoroscopo.data

import com.darksoft.mihoroscopo.core.network.ErrorType
import com.darksoft.mihoroscopo.core.network.ResultType
import com.darksoft.mihoroscopo.data.network.MiHoroscopoApi
import com.darksoft.mihoroscopo.data.network.model.toDomain
import com.darksoft.mihoroscopo.domain.dto.HoroscopeDto
import com.darksoft.mihoroscopo.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val api: MiHoroscopoApi): BaseRepository() {

    // Coroutines basica
    suspend fun getHoroscope(horoscopeDto: HoroscopeDto): ResultType<HoroscopeModel> {
        return try {
            val response = api.getHoroscope(horoscopeDto.sign, horoscopeDto.date, horoscopeDto.lang)
            if (response.isSuccessful) {
                response.body()?.let { horoscopeResponse ->
                    ResultType.Success(horoscopeResponse.toDomain())
                } ?:  ResultType.Error(ErrorType.UncontrolledError(-1))
                // Nuestro body no esta pasando el if, por eso no se ejecuta el codigo de arriba
            } else {
                val error = when (response.code()) {
                    ErrorType.BadRequest.errorCode -> ErrorType.BadRequest
                    ErrorType.Unauthorized.errorCode -> ErrorType.Unauthorized
                    ErrorType.Forbidden.errorCode -> ErrorType.Forbidden
                    ErrorType.NotFound.errorCode -> ErrorType.NotFound
                    ErrorType.InternalServerError.errorCode -> ErrorType.InternalServerError
                    else -> ErrorType.UncontrolledError(response.code())
                }
                ResultType.Error(error)
            }
        } catch (e: Exception) {
            ResultType.Error(ErrorType.ExceptionError(e))
        }
    }


    // FLOW AVANZADO
//    fun getHoroscope(horoscopeDto: HoroscopeDto) = runApiCallFlow {
//        api.getHoroscope(horoscopeDto.sign, horoscopeDto.date, horoscopeDto.lang)
//    }

    // FLOW BASICO
//    fun getHoroscope(horoscopeDto: HoroscopeDto): Flow<ResultType<HoroscopeModel>> = flow {
//        try {
//            val response = api.getHoroscope(horoscopeDto.sign, horoscopeDto.date, horoscopeDto.lang)
//            if (response.isSuccessful) {
//                response.body()?.let { horoscopeResponse ->
//                    emit(ResultType.Success(horoscopeResponse.toDomain()))
//                }
//            } else {
//                val message = when (response.code()) {
//                    401 -> "No autorizado"
//                    404 -> "No se encontro el recurso"
//                    500 -> "Error en el servidor"
//                    else -> "Error desconocido"
//                }
//                emit(ResultType.Error(message))
//            }
//        } catch (e: Exception) {
//            val message = when (e) {
//                is java.net.UnknownHostException -> "No hay conexion a internet"
//                is IOException -> "Error de lectura"
//                else -> "Error de lectura"
//            }
//            emit(ResultType.Error(message))
//        }
//    }

}