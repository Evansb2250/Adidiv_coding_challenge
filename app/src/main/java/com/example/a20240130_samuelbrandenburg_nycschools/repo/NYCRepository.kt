package com.example.a20240130_samuelbrandenburg_nycschools.repo

import android.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.a20240130_samuelbrandenburg_nycschools.NetworkResponse
import com.example.a20240130_samuelbrandenburg_nycschools.ap_service.schools_api.NYCSchoolApiService
import com.example.a20240130_samuelbrandenburg_nycschools.models.School
import com.example.a20240130_samuelbrandenburg_nycschools.models.SchoolDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

interface SchoolRepository {
    suspend fun getSchools(): NetworkResponse<List<School>>
    suspend fun getSchoolDetails(
        id: String,
    ): NetworkResponse<SchoolDetails>
}

@Singleton
class NYCRepositoryImpl(
    private val nycSchoolApiService: NYCSchoolApiService
) : SchoolRepository {

    override suspend fun getSchools(): NetworkResponse<List<School>> {
        return withContext(Dispatchers.IO) {
            val response = nycSchoolApiService.getSchools()
            if (response.isNotEmpty()) {
                NetworkResponse.Success(data = response)
            } else
                NetworkResponse.Failed(errorMessage = "no data found")
        }
    }


    override suspend fun getSchoolDetails(
        id: String,
    ): NetworkResponse<SchoolDetails> {
        val schools = withContext(Dispatchers.IO) {
            nycSchoolApiService.getSchoolDetails()
        }
        val schoolDetail = schools.firstOrNull { it.id.equals(id) }
        return if (schoolDetail != null) NetworkResponse.Success(data = schoolDetail) else NetworkResponse.Failed(
            errorMessage = "Id not found"
        )
    }
}