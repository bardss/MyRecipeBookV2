package com.jakubaniola.database.dao

import com.jakubaniola.database.MrbDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providesRecipesDao(
        database: MrbDatabase,
    ): RecipeDao = database.recipeDao()
}