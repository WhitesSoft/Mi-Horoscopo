package com.darksoft.mihoroscopo.domain

import com.darksoft.mihoroscopo.core.network.ResultType
import com.darksoft.mihoroscopo.data.network.HoroscopeRepository
import com.darksoft.mihoroscopo.domain.dto.HoroscopeDto
import com.darksoft.mihoroscopo.domain.model.HoroscopeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHoroscopeUseCase @Inject constructor(private val horoscopeRepository: HoroscopeRepository) {

    operator fun invoke(horoscopeDto: HoroscopeDto): Flow<ResultType<HoroscopeModel>> =
        horoscopeRepository.getHoroscope(horoscopeDto)

}