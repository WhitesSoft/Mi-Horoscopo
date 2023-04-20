package com.darksoft.mihoroscopo.domain

import com.darksoft.mihoroscopo.data.network.MiHoroscopoApi
import com.darksoft.mihoroscopo.data.network.model.HoroscopoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val api: MiHoroscopoApi) {

    suspend operator fun invoke(): Flow<HoroscopoResponse?> {
        val response = api.getHoroscopo("virgo", "today")
        if (response.isSuccessful) {
            return flowOf(response.body())
        }
        return flowOf(null)
    }

}