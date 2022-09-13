package com.karlosprojects.foodrecipes.data.di

import com.karlosprojects.foodrecipes.data.api.SpoonacularApi
import com.karlosprojects.foodrecipes.data.repository.RecipesRepositoryImpl
import com.karlosprojects.foodrecipes.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipesModule {

    @Provides
    @Singleton
    fun providesRecipesRepository(
        dataSource: SpoonacularApi
    ): RecipesRepository {
        return RecipesRepositoryImpl(dataSource)
    }
}