package com.mshdabiola.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mshdabiola.ui.NotifySnacker
import timber.log.Timber

@Composable
internal fun MainScreen(viewModel: MainViewModel = hiltViewModel(), onBack: () -> Unit) {
    val mainState= viewModel.mainState.collectAsStateWithLifecycle()
    MainScreen(
        back = onBack,
        mainState = mainState.value,

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    mainState: MainState,
    back: () -> Unit = {},
) {
    val snackbarHostState= remember {
        SnackbarHostState()
    }
    LaunchedEffect(key1 = mainState.messages, block = {
        Timber.d(mainState.messages.joinToString ())
    })
    NotifySnacker(snackHostState = snackbarHostState, notifys = mainState.messages)
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
        snackbarHost = { SnackbarHost(snackbarHostState)}
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                placeholder = { Text(text = "Enter text") },
                onValueChange = {},
            )
            Button(onClick = {}) {
                Text(text = "Add Test")
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(mainState = MainState())
}
