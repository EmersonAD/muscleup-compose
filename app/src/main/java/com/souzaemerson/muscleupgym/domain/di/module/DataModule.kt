package com.souzaemerson.muscleupgym.domain.di.module

import com.souzaemerson.muscleupgym.data.source.remote.ExerciseDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindExerciseDataSource(
        exerciseDataSource: ExerciseDataSource
    ): ExerciseDataSource
}