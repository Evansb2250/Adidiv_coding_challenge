package com.example.a20240130_samuelbrandenburg_nycschools.ap_service.schools_api

import com.example.a20240130_samuelbrandenburg_nycschools.models.School
import com.example.a20240130_samuelbrandenburg_nycschools.models.SchoolDetails
import retrofit2.http.GET

interface NYCSchoolApiService {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): List<School>

    @GET("resource/f9bf-2cp4.json")
    suspend fun getSchoolDetails(): List<SchoolDetails>
}