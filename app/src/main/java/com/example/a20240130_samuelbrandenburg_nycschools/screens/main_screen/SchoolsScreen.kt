package com.example.a20240130_samuelbrandenburg_nycschools.screens.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a20240130_samuelbrandenburg_nycschools.models.School

@Composable
fun SchoolScreen(
    navigateToDetails: (String) -> Unit = {},
) {
    val viewModel = hiltViewModel<SchoolScreenViewModel>()
    val state = viewModel.schoolsState.collectAsState()
    SchoolScreenContent(
        state = state.value,
        navigateToDetails = navigateToDetails
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SchoolScreenContent(
    state: SchoolScreenStates,
    navigateToDetails: (String) -> Unit = {},
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (state) {
            is SchoolScreenStates.FailedState -> Text(text = state.errorMessage)
            is SchoolScreenStates.Loading -> CircularProgressIndicator()
            is SchoolScreenStates.SuccessState -> {
                LazyColumn() {
                    items(state.schoolDetails.size) { index ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(
                                    all = 16.dp,
                                ),
                            onClick = {
                                navigateToDetails(state.schoolDetails[index].id)
                            }
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                                ){
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "${state.schoolDetails[index].name}",
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}