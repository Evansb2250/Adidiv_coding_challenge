package com.example.a20240130_samuelbrandenburg_nycschools.di

import com.example.a20240130_samuelbrandenburg_nycschools.repo.NYCRepositoryImpl
import com.example.a20240130_samuelbrandenburg_nycschools.screens.details_screen.SchoolDetailViewModel
import com.example.a20240130_samuelbrandenburg_nycschools.screens.main_screen.SchoolScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {
    @Provides
    fun providesSchoolScreenViewModel(
        schoolRepo: NYCRepositoryImpl
    ): SchoolScreenViewModel = SchoolScreenViewModel(
        schoolRepo = schoolRepo
    )

    @Provides
    fun provideSchoolDetailViewModel(
        schoolRepo: NYCRepositoryImpl,
    ): SchoolDetailViewModel = SchoolDetailViewModel(
        schoolRepo
    )
}