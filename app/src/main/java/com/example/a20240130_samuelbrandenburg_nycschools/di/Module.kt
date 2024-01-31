package com.example.a20240130_samuelbrandenburg_nycschools.di

import com.example.a20240130_samuelbrandenburg_nycschools.ap_service.schools_api.NYCApiSchoolClient
import com.example.a20240130_samuelbrandenburg_nycschools.ap_service.schools_api.NYCSchoolApiService
import com.example.a20240130_samuelbrandenburg_nycschools.repo.NYCRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesNYCRepository(
        nycSchoolApiService: NYCSchoolApiService
    ): NYCRepositoryImpl =
        NYCRepositoryImpl(
            nycSchoolApiService
        )


    @Provides
    fun providesNycSchoolApiService(): NYCSchoolApiService =
        NYCApiSchoolClient.getService()
}