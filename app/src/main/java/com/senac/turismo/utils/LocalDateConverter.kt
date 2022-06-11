package com.senac.turismo.utils

import androidx.room.TypeConverter
import java.time.LocalDate

object LocalDateConverter {
    @TypeConverter
    fun longToLocalDate(long: Long?): LocalDate? {
        return long?.let { LocalDate.ofEpochDay(long) }
    }

    @TypeConverter
    fun dateToLong(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}