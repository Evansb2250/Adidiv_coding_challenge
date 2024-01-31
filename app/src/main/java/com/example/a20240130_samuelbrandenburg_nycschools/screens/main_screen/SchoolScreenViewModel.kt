package com.example.a20240130_samuelbrandenburg_nycschools.screens.main_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20240130_samuelbrandenburg_nycschools.NetworkResponse
import com.example.a20240130_samuelbrandenburg_nycschools.models.School
import com.example.a20240130_samuelbrandenburg_nycschools.models.SchoolDetails
import com.example.a20240130_samuelbrandenburg_nycschools.repo.SchoolRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolScreenViewModel @Inject constructor(
    val schoolRepo: SchoolRepository,
) : ViewModel() {

    private var _schoolsState = MutableStateFlow<SchoolScreenStates>(SchoolScreenStates.Loading)
    val  schoolsState = _schoolsState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response: NetworkResponse<List<School>> = schoolRepo.getSchools()
            when(response){
                is NetworkResponse.Failed -> _schoolsState.update {
                    SchoolScreenStates.FailedState(response.errorMessage)
                }
                is NetworkResponse.Success -> _schoolsState.update {
                    SchoolScreenStates.SuccessState( response.data)
                }
            }
        }
    }
}


sealed class SchoolScreenStates {
    object Loading: SchoolScreenStates()
    data class SuccessState(val schoolDetails: List<School>): SchoolScreenStates()
    data class FailedState(val errorMessage: String): SchoolScreenStates()
}