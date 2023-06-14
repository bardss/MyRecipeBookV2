package com.jakubaniola.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jakubaniola.database.dao.RecipeDao
import com.jakubaniola.database.entity.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class MrbDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
