package com.darksoft.mihoroscopo.data.network.model

import com.darksoft.mihoroscopo.domain.model.HoroscopeModel
import com.google.gson.annotations.SerializedName

data class HoroscopeResponse(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("sign") val sign: String
)

// Mapeamos los datos para la capa domain (tiene que tener el mismo nombre de la clase)
fun HoroscopeResponse.toDomain() = HoroscopeModel(
    horoscope = this.horoscope,
    icon = this.icon,
    sign = this.sign
)
