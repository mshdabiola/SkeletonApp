package com.mshdabiola.mainscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.mshdabiola.data.repository.ModelRepository
import com.mshdabiola.model.Model
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val modelRepository: ModelRepository,
) : ViewModel() {

    val mainState = modelRepository
        .getModels()
//        .asResult()
//        .stateIn(scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(3000), initialValue = MainState.Loading)

    val model=modelRepository
        .getModelPaging()
        .map { it.map { it.asModelUiState() } }
        .cachedIn(viewModelScope)
    fun insert(name:String) {
        viewModelScope.launch(Dispatchers.IO) {
            modelRepository.insertModel(Model(name = name))
        }
    }
}
