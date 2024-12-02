package com.alva.booklist.models

import android.os.Build
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
data class Book(val index: Int, val title: String, val date: String, val description: String) {
    fun getDateLocalDate(): LocalDate? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    } else null
}