package com.souzaemerson.muscleupgym.domain.di.module

import com.souzaemerson.muscleupgym.data.source.local.bmi.BmiDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class BmiModule {

    @Provides
    fun bindBmiDataSource() = BmiDataSource()
}