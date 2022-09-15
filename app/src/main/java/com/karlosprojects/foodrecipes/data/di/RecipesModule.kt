package com.karlosprojects.foodrecipes.data.di

import com.karlosprojects.foodrecipes.data.api.SpoonacularApi
import com.karlosprojects.foodrecipes.data.local.dao.RecipeDao
import com.karlosprojects.foodrecipes.data.local.datastore.DataStorePreferences
import com.karlosprojects.foodrecipes.data.repository.DataStoreRepositoryImpl
import com.karlosprojects.foodrecipes.data.repository.RecipesRepositoryImpl
import com.karlosprojects.foodrecipes.domain.repository.DataStoreRepository
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
        remoteDataSource: SpoonacularApi,
        localDataSource: RecipeDao
    ): RecipesRepository {
        return RecipesRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun providesDatastoreRepository(
        preferences: DataStorePreferences
    ): DataStoreRepository {
        return DataStoreRepositoryImpl(preferences)
    }
}