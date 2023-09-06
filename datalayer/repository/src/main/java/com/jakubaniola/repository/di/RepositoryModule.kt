package com.jakubaniola.repository.di

import com.jakubaniola.repository.RecipeRepository
import com.jakubaniola.repository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindsTopicRepository(
        recipeRepository: RecipeRepositoryImpl,
    ): RecipeRepository
}
