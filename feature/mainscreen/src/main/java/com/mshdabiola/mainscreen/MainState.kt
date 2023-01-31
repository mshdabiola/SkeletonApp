package com.mshdabiola.mainscreen

import com.mshdabiola.model.Model

data class ModelUiState(val id: Long, val name: String)

 fun Model.asModelUiState()=ModelUiState(id!!,name)
