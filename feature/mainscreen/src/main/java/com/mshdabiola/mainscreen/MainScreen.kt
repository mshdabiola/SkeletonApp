package com.mshdabiola.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mshdabiola.ui.NotifySnacker
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

@Composable
internal fun MainScreen(viewModel: MainViewModel = hiltViewModel(), onBack: () -> Unit) {
    val mainState= viewModel.mainState.collectAsStateWithLifecycle()
    val modelst = viewModel.modelState.collectAsLazyPagingItems()
    MainScreen(
        back = onBack,
        mainState = mainState.value,
        items = modelst,
        setName =viewModel::addName
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun MainScreen(
    mainState: MainState=MainState(),
    back: () -> Unit = {},
    items : LazyPagingItems<ModelUiState>,
    setName:(String)->Unit={}
) {
    val snackbarHostState= remember {
        SnackbarHostState()
    }
    LaunchedEffect(key1 = mainState.messages, block = {
        Timber.d(mainState.messages.joinToString ())
    })
    var name by remember {
        mutableStateOf("")
    }
    NotifySnacker(snackHostState = snackbarHostState, notifys = mainState.messages)
    Scaffold(
        modifier=Modifier.semantics { this.testTagsAsResourceId=true },
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
        snackbarHost = { SnackbarHost(snackbarHostState)}
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                placeholder = { Text(text = "Enter text") },
                onValueChange = { name=it},
            )
            Button(
                modifier = Modifier.testTag("button"),
                onClick = {
                    setName(name)
                    name=""

                }) {
                Text(text = "Add Test")
            }
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)){
                items(items){
                    Text(text = it?.name ?: "null")
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        mainState = MainState(),
        items = flowOf(PagingData.from(listOf(ModelUiState(2,"")))).collectAsLazyPagingItems()
    )
}
