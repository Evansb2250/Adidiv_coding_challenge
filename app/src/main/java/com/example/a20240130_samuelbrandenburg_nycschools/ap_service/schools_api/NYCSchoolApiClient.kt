package com.example.a20240130_samuelbrandenburg_nycschools.ap_service.schools_api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


private const val BASE_URL = "https://data.cityofnewyork.us/"

class NYCApiSchoolClient {

    companion object{
        private val json = Json { ignoreUnknownKeys = true }
        private lateinit var instance: NYCSchoolApiService
        fun getService(): NYCSchoolApiService {
            if(!::instance.isInitialized){
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                    .build()

                instance = retrofit.create(NYCSchoolApiService::class.java)
            }
            return instance
        }
    }

}