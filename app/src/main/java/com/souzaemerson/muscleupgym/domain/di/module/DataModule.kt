package com.souzaemerson.muscleupgym.domain.di.module

import com.souzaemerson.muscleupgym.data.source.local.bmi.BmiDataSource
import com.souzaemerson.muscleupgym.data.source.local.bmi.BmiDataSourceImpl
import com.souzaemerson.muscleupgym.data.source.remote.ExerciseDataSource
import com.souzaemerson.muscleupgym.data.source.remote.ExerciseDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindExerciseDataSource(
        exerciseDataSourceImpl: ExerciseDataSourceImpl
    ): ExerciseDataSource
}