package com.mshdabiola.mainscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mshdabiola.common.result.asResult
import com.mshdabiola.data.repository.ModelRepository
import com.mshdabiola.model.Model
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val modelRepository: ModelRepository
) : ViewModel(){

    val mainState = modelRepository
        .getModels()
        .asResult()
        .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3000), initialValue = MainState.Loading)

     fun insert(model : Model){
        viewModelScope.launch(Dispatchers.IO) {
             modelRepository.insertModel(model)
         }
     }

}
