package com.darksoft.mihoroscopo.domain

import com.darksoft.mihoroscopo.core.network.ResultType
import com.darksoft.mihoroscopo.data.HoroscopeRepository
import com.darksoft.mihoroscopo.data.network.model.toDomain
import com.darksoft.mihoroscopo.domain.dto.HoroscopeDto
import com.darksoft.mihoroscopo.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val horoscopeRepository: HoroscopeRepository) {

    // Metodo para flows
//    operator fun invoke(horoscopeDto: HoroscopeDto): Flow<ResultType<HoroscopeModel>> =
//        horoscopeRepository.getHoroscope(horoscopeDto)

    // Metodo para coroutines
    suspend operator fun invoke(HoroscopeDto: HoroscopeDto): ResultType<HoroscopeModel> =
        horoscopeRepository.getHoroscope(HoroscopeDto)


}