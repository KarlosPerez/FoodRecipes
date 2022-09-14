package com.karlosprojects.foodrecipes.data.local.di

import android.content.Context
import androidx.room.Room
import com.karlosprojects.foodrecipes.core.utils.Constants.DATABASE_NAME
import com.karlosprojects.foodrecipes.data.local.RecipesDatabase
import com.karlosprojects.foodrecipes.data.local.converters.RecipeTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        DATABASE_NAME
    )
        .addTypeConverter(RecipeTypeConverter())
        .build()

    @Provides
    @Singleton
    fun provideDao(database: RecipesDatabase) = database.recipesDao()
}