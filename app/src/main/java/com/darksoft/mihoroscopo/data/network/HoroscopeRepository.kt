package com.darksoft.mihoroscopo.data.network

import com.darksoft.mihoroscopo.core.network.ResultType
import com.darksoft.mihoroscopo.data.network.model.toDomain
import com.darksoft.mihoroscopo.domain.dto.HoroscopeDto
import com.darksoft.mihoroscopo.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(private val api: MiHoroscopoApi) {

    fun getHoroscope(horoscopeDto: HoroscopeDto): Flow<ResultType<HoroscopeModel>> = flow {
        try {
            val response = api.getHoroscope(horoscopeDto.sign, horoscopeDto.date, horoscopeDto.lang)
            if (response.isSuccessful) {
                response.body()?.let { horoscopeResponse ->
                    emit(ResultType.Success(horoscopeResponse.toDomain()))
                }
            } else {
                val message = when (response.code()) {
                    401 -> "No autorizado"
                    404 -> "No se encontro el recurso"
                    500 -> "Error en el servidor"
                    else -> "Error desconocido"
                }
                emit(ResultType.Error(message))
            }
        } catch (e: Exception) {
            val message = when (e) {
                is java.net.UnknownHostException -> "No hay conexion a internet"
                is IOException -> "Error de lectura"
                else -> "Error de lectura"
            }
            emit(ResultType.Error(message))
        }
    }

}