package com.jakubaniola.database

import androidx.room.TypeConverter

private const val SEPARATOR = ":"

class Converters {

    @TypeConverter
    fun fromListString(value: List<String>): String =
        value.joinToString(SEPARATOR)

    @TypeConverter
    fun toListString(value: String): List<String> =
        value.split(SEPARATOR)
}
