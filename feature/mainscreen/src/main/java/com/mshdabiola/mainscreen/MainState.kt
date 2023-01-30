package com.mshdabiola.mainscreen

sealed interface MainState {
    data class Show(val models: List<ModelUiState>) : MainState
    object Error : MainState

    object Loading : MainState
}

data class ModelUiState(val id: Long, val name: String)
