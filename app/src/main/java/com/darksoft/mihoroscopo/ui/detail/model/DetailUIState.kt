package com.darksoft.mihoroscopo.ui.detail.model

import com.darksoft.mihoroscopo.domain.model.HoroscopeModel

// Estado de la pantalla UI
sealed class DetailUIState {

    object Loading : DetailUIState()
    data class Success(val horoscopeModel: HoroscopeModel) : DetailUIState()
    data class Error(val msg: String) : DetailUIState()

}