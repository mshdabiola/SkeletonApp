package com.mshdabiola.mainscreen

sealed interface MainState {
    data class Show(val models: List<ModelUiState>) : MainState
    object Error : MainState

    object Loading : MainState
}

data class MainState(
    val messages:ImmutableList<Notify> = emptyList<Notify>().toImmutableList()
)

 fun Model.asModelUiState()=ModelUiState(id!!,name)
