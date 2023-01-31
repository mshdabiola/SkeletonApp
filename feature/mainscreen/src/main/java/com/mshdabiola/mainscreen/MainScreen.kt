package com.mshdabiola.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun MainScreen(viewModel: MainViewModel = hiltViewModel(), onBack: () -> Unit) {
val m=viewModel.model.collectAsLazyPagingItems()
    MainScreen(
list=m,
        back = onBack,
        setName=viewModel::insert
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    list:LazyPagingItems<ModelUiState> = flowOf(PagingData.from(listOf(ModelUiState(3,"abiola"))) )
        .collectAsLazyPagingItems(),
    back: () -> Unit = {},
    setName :(String)->Unit={}
) {
    var name by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                },
                title = {
                    Text(text = "name")
                },
            )
        },
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                placeholder = { Text(text = "Enter text") },
                onValueChange = {name=it},
            )
            Button(onClick = {
                if(name.isNotBlank()) {
                    setName(name)
                    name = ""
                }
            }) {
                Text(text = "Add Test")
            }
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)){
                items(list){
                    Text(text = it?.name ?: "null")
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
