package com.karlosprojects.foodrecipes.core.utils

sealed class UiEvent {
    object NavigateUp: UiEvent()
    object ShowEmptyState: UiEvent()
    data class ShowSnackBar(val message: UiText): UiEvent()
}
