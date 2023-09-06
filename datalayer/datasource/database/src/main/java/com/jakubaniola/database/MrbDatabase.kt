package com.jakubaniola.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jakubaniola.database.dao.RecipeDao
import com.jakubaniola.database.entity.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class,
    ],
    version = 2,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class MrbDatabase : RoomDatabase() {
    internal abstract fun recipeDao(): RecipeDao
}
