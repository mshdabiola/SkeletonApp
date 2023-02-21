package com.mshdabiola.mainscreen

import com.mshdabiola.ui.data.Notify
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

//sealed interface MainState {
//    data class Show(val models: List<ModelUiState>) : MainState
//    object Error : MainState
//
//    object Loading : MainState
//}

data class MainState(
    val messages:ImmutableList<Notify> = emptyList<Notify>().toImmutableList()
)
