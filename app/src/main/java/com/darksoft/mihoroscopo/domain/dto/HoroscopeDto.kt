package com.darksoft.mihoroscopo.domain.dto

import java.text.SimpleDateFormat
import java.util.*

data class HoroscopeDto(val sign: String, val date: String = currentDate(), val lang: String = "es")

private fun currentDate(): String {
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(currentDate)
}
