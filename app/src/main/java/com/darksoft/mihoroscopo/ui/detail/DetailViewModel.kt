package com.darksoft.mihoroscopo.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darksoft.mihoroscopo.data.network.MiHoroscopoApi
import com.darksoft.mihoroscopo.domain.GetHoroscopeUseCase
import com.darksoft.mihoroscopo.ui.detail.model.DetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getHoroscopeUseCase: GetHoroscopeUseCase) : ViewModel() {

    // Creamos el estado de la pantalla
    private val _uiState = MutableStateFlow<DetailUIState>(DetailUIState.Loading) // Estado inicial
    val uiState: StateFlow<DetailUIState> = _uiState // El usuario solo leera el estado de la pantalla

    // Obtener el horoscopo
    fun getHoroscope() {

        // Creamos una corutina a nivel del viewModel
        viewModelScope.launch {
            getHoroscopeUseCase()
                .collect{ horoscopo ->
                    // Aqui recibimos la informacion, cada cambio que se realice en el flow
                    if (horoscopo != null) {
                        // Aqui recibimos la informacion
                        _uiState.value = DetailUIState.Success(horoscopo)
                    }
                }
        }

    }
}