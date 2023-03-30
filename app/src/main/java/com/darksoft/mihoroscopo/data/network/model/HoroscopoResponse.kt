package com.darksoft.mihoroscopo.data.network.model

import com.google.gson.annotations.SerializedName

data class HoroscopoResponse(
    @SerializedName("current_date") val currentDate: String,
    @SerializedName("compatibility") val compatibility: String,
    @SerializedName("lucky_time") val luckyTime: String,
    @SerializedName("color") val color: String,
    @SerializedName("date_range") val dateRange: String,
    @SerializedName("mood") val mood: String,
    @SerializedName("description") val description: String,
)