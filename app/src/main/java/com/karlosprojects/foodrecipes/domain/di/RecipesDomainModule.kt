package com.karlosprojects.foodrecipes.domain.di

import com.karlosprojects.foodrecipes.domain.repository.RecipesRepository
import com.karlosprojects.foodrecipes.domain.usecases.GetRecipes
import com.karlosprojects.foodrecipes.domain.usecases.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RecipesDomainModule {

    @Provides
    @ViewModelScoped
    fun providesRecipesUseCase(
        repository: RecipesRepository
    ): GetRecipes {
        return GetRecipes(repository)
    }

    @Provides
    @ViewModelScoped
    fun providesSearchRecipesUseCase(
        repository: RecipesRepository
    ): SearchRecipes {
        return SearchRecipes(repository)
    }
}