package com.jakubaniola.database

import androidx.room.TypeConverter

private const val SEPARATOR = ":"

class Converters {

    @TypeConverter
    fun fromListString(value: List<String>): String =
        value.joinToString(SEPARATOR)

    @TypeConverter
    fun toListString(value: String): List<String> =
        if (value.isNotEmpty()) value.split(SEPARATOR) else listOf()
}
