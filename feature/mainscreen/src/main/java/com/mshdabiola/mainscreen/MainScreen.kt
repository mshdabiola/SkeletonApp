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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun MainScreen(viewModel: MainViewModel = hiltViewModel(), onBack: () -> Unit) {
    MainScreen(back = onBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainScreen(
    mainState: MainState = MainState.Loading,
    back: () -> Unit = {},
) {
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
                value = "",
                placeholder = { Text(text = "Enter text") },
                onValueChange = {},
            )
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add Test")
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}
