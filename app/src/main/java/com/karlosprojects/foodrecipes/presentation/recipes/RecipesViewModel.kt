package com.karlosprojects.foodrecipes.presentation.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karlosprojects.foodrecipes.core.utils.UiEvent
import com.karlosprojects.foodrecipes.data.local.datastore.DataStoreParameters
import com.karlosprojects.foodrecipes.domain.repository.DataStoreRepository
import com.karlosprojects.foodrecipes.domain.usecases.GetRecipes
import com.karlosprojects.foodrecipes.domain.usecases.SearchRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUC: GetRecipes,
    private val searchRecipesUC: SearchRecipes,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _datastorePreferences = Channel<DataStoreParameters>()
    val datastorePreferences = _datastorePreferences.receiveAsFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _recipesState = MutableStateFlow(RecipesUiState())
    val recipesState: StateFlow<RecipesUiState> = _recipesState

    fun getSavedMealAndDietType() = viewModelScope.launch {
        dataStoreRepository.getMealAndTypePreferences().collectLatest { preferences ->
            _datastorePreferences.send(preferences)
        }
    }

    fun saveMealAndTypePreferences(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndTypePreferences(
                DataStoreParameters(
                    name = "",
                    mealType = mealType,
                    mealTypeId = mealTypeId,
                    dietType = dietType,
                    dietTypeId = dietTypeId,
                    backOnline = true
                )
            )
        }
    }

    fun getRecipes() = viewModelScope.launch {
        _recipesState.value = _recipesState.value.copy(isLoading = true)
        dataStoreRepository.getMealAndTypePreferences().collectLatest { preferences ->
            getRecipesUC.invoke(preferences)
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

    fun searchRecipes(query: String) = viewModelScope.launch {
        _recipesState.value = _recipesState.value.copy(isLoading = true)
            searchRecipesUC(query)
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
