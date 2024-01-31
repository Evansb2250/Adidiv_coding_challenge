package com.example.a20240130_samuelbrandenburg_nycschools.screens.details_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a20240130_samuelbrandenburg_nycschools.models.SchoolDetails

@Composable
fun SchoolDetailScreen(
    screenId: String,
    viewModel: SchoolDetailViewModel = hiltViewModel<SchoolDetailViewModel>()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.load(screenId)
    }
    DetailContent(
        state = viewModel.state
    )

}

@Composable
private fun DetailContent(
    state: ScreenDetailState
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        ) {
        Column {
            when (state) {
                is ScreenDetailState.FailedState -> {
                    Text(text = state.errorMessage)
                }

                ScreenDetailState.Loading -> {
                    CircularProgressIndicator()
                }

                is ScreenDetailState.SuccessState -> {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                    ) {

                        Row {
                            Text(
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f),
                                text = "School name:"
                            )
                            Text(
                                modifier = Modifier.weight(2f),
                                text = "${state.schoolDetails.schoolName}"
                            )
                        }
                        Row {
                            Text(
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(2f),
                                text = "School math score:",
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = "${state.schoolDetails.mathScore}"
                            )
                        }
                        Row {
                            Text(
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(2f),
                                text = "School reading score:",
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = " ${state.schoolDetails.readingScore}",
                            )
                        }
                        Row {
                            Text(
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(2f),
                                text = "School writing score:",
                            )
                            Text(
                                modifier = Modifier.weight(1f),
                                text = " ${state.schoolDetails.writingScore}"
                            )
                        }
                    }
                }
            }
        }
    }
}