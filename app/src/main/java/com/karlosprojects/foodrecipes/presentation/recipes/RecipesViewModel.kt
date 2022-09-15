package com.karlosprojects.foodrecipes.presentation.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karlosprojects.foodrecipes.core.utils.UiEvent
import com.karlosprojects.foodrecipes.domain.usecases.GetRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUC: GetRecipes
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _recipesState = MutableStateFlow(RecipesUiState())
    val recipesState: StateFlow<RecipesUiState> = _recipesState

    fun getRecipes() = viewModelScope.launch {
        _recipesState.value = _recipesState.value.copy(isLoading = true)
        getRecipesUC()
            .onSuccess { recipes ->
                _recipesState.value = _recipesState.value.copy(
                    isLoading = false,
                    recipes = recipes
                )
            }
            .onFailure {
                _recipesState.value = _recipesState.value.copy(isLoading = false)
                _uiEvent.send(UiEvent.ShowEmptyState)
            }
    }

}
