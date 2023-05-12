package com.darksoft.mihoroscopo.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darksoft.mihoroscopo.core.network.ErrorType
import com.darksoft.mihoroscopo.core.network.ResultType
import com.darksoft.mihoroscopo.domain.GetHoroscopeUseCase
import com.darksoft.mihoroscopo.domain.dto.HoroscopeDto
import com.darksoft.mihoroscopo.ui.detail.model.DetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getHoroscopeUseCase: GetHoroscopeUseCase) :
    ViewModel() {

    // Creamos el estado de la pantalla
    private val _uiState = MutableStateFlow<DetailUIState>(DetailUIState.Loading) // Estado inicial
    val uiState: StateFlow<DetailUIState> =
        _uiState // El usuario solo leera el estado de la pantalla

    // Funcion para las corrutinas
    fun getHoroscope() {

        // main = UI
        // IO = Room, Retrofit, etc (Backends)
        // Default = Procesos largos
        viewModelScope.launch(Dispatchers.IO) {
            // Inicia la corrutina
            when (val response = getHoroscopeUseCase(HoroscopeDto(sign = "virgo"))) {
                is ResultType.Error -> _uiState.value = DetailUIState.Error("Error")
                is ResultType.Success -> _uiState.value = DetailUIState.Success(response.data!!)
            }
        }

    }

//    Funcion preparada para los flows
//    // Obtener el horoscopo
//    fun getHoroscope() {
//
//        // Creamos una corrutina a nivel del viewModel
//        viewModelScope.launch {
//            getHoroscopeUseCase(HoroscopeDto(sign = "virgo"))
//                .collect{ result: ResultType<HoroscopeModel> ->
//                    // Aqui recibimos la informacion, cada cambio que se realice en el flow
//                    when(result) {
//                        is ResultType.Error -> {
//                            val error = when(result.errorType){
//                                ErrorType.BadRequest -> "Petición incorrecta"
//                                ErrorType.Unauthorized -> "No estas autorizado"
//                                ErrorType.Forbidden -> "No tienes permisos"
//                                ErrorType.NotFound -> "No se encontro el recurso"
//                                ErrorType.InternalServerError ->"Error en el servidor"
//                                is ErrorType.UncontrolledError -> "Codigo de error: ${result.errorType.errorCode}"
//                                is ErrorType.ExceptionError -> "Error desconocido"
//                            }
//                            _uiState.value = DetailUIState.Error(error)
//                        }
//                        is ResultType.Success -> {
//                            _uiState.value = DetailUIState.Success(result.data!!)
//                        }
//                    }
//                }
//        }
//
//    }
}