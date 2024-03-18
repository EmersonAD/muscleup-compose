package com.souzaemerson.muscleupgym.domain.di.module

import com.souzaemerson.muscleupgym.data.repository.AnnotationsRepositoryImpl
import com.souzaemerson.muscleupgym.data.repository.ExercisesRepositoryImpl
import com.souzaemerson.muscleupgym.data.repository.GetAllExercisesRepositoryImpl
import com.souzaemerson.muscleupgym.data.repository.GetBodyPartListRepositoryImpl
import com.souzaemerson.muscleupgym.data.repository.GetSelectedExercisesRepositoryImpl
import com.souzaemerson.muscleupgym.domain.repository.AnnotationsRepository
import com.souzaemerson.muscleupgym.domain.repository.ExercisesRepository
import com.souzaemerson.muscleupgym.domain.repository.GetAllExercisesRepository
import com.souzaemerson.muscleupgym.domain.repository.GetBodyPartListRepository
import com.souzaemerson.muscleupgym.domain.repository.GetSelectedExercisesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAnnotationsRepository(
        annotationsRepositoryImpl: AnnotationsRepositoryImpl
    ): AnnotationsRepository

    @Binds
    abstract fun bindAllExercisesRepository(
        getSelectedBodyPartRepository: GetAllExercisesRepositoryImpl
    ): GetAllExercisesRepository

    @Binds
    abstract fun bindGetBodyPartListRepository(
        getBodyPartListRepositoryImpl: GetBodyPartListRepositoryImpl
    ): GetBodyPartListRepository

    @Binds
    abstract fun bindGetSelectedExerciseRepository(
        getSelectedExercisesRepository: GetSelectedExercisesRepositoryImpl
    ): GetSelectedExercisesRepository

    @Binds
    abstract fun bindExercisesRepository(
        exercisesRepository: ExercisesRepositoryImpl
    ): ExercisesRepository

}