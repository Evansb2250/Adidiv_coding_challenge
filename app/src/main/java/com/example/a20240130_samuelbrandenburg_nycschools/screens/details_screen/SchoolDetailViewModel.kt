package com.example.a20240130_samuelbrandenburg_nycschools.screens.details_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20240130_samuelbrandenburg_nycschools.NetworkResponse
import com.example.a20240130_samuelbrandenburg_nycschools.models.SchoolDetails
import com.example.a20240130_samuelbrandenburg_nycschools.repo.NYCRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ScreenDetailState {
    object Loading: ScreenDetailState()
    data class SuccessState(val schoolDetails: SchoolDetails): ScreenDetailState()
    data class FailedState(val errorMessage: String): ScreenDetailState()
}

@HiltViewModel
class SchoolDetailViewModel @Inject constructor(
    val schoolRepo: NYCRepositoryImpl,
) : ViewModel() {

    var state: ScreenDetailState by mutableStateOf(ScreenDetailState.Loading)
        private set

    fun load(
        schoolId: String,
    ) {
        viewModelScope.launch {
            val response = schoolRepo.getSchoolDetails(schoolId)
            when (response) {
                is NetworkResponse.Failed -> state =
                    ScreenDetailState.FailedState(response.errorMessage)

                is NetworkResponse.Success -> state = ScreenDetailState.SuccessState(response.data)
            }
        }

    }
}