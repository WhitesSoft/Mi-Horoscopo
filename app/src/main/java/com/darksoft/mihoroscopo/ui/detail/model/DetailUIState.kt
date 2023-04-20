package com.darksoft.mihoroscopo.ui.detail.model

import com.darksoft.mihoroscopo.data.network.model.HoroscopoResponse

// Estado de la pantalla UI
sealed class DetailUIState {

    object Loading : DetailUIState()
    data class Success(val horoscope: HoroscopoResponse) : DetailUIState()
    data class Error(val msg: String) : DetailUIState()

}