package com.souzaemerson.muscleupgym.domain.di.module

import com.souzaemerson.muscleupgym.data.usecase.GetAllExercisesUseCaseImpl
import com.souzaemerson.muscleupgym.data.usecase.GetBodyPartListUseCaseImpl
import com.souzaemerson.muscleupgym.data.usecase.GetSelectedExercisesUseCaseImpl
import com.souzaemerson.muscleupgym.data.usecase.InsertAnnotationUseCaseImpl
import com.souzaemerson.muscleupgym.data.usecase.RemoveAnnotationUseCaseImpl
import com.souzaemerson.muscleupgym.data.usecase.UpdateAnnotationUseCaseImpl
import com.souzaemerson.muscleupgym.domain.usecase.GetAllExercisesUseCase
import com.souzaemerson.muscleupgym.domain.usecase.GetBodyPartListUseCase
import com.souzaemerson.muscleupgym.domain.usecase.GetSelectedExercisesUseCase
import com.souzaemerson.muscleupgym.domain.usecase.InsertAnnotationUseCase
import com.souzaemerson.muscleupgym.domain.usecase.RemoveAnnotationUseCase
import com.souzaemerson.muscleupgym.domain.usecase.UpdateAnnotationUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindUpdateAnnotation(
        updateAnnotationUseCaseImpl: UpdateAnnotationUseCaseImpl
    ): UpdateAnnotationUseCase

    @Binds
    abstract fun bindInsertAnnotation(
        insertAnnotationUseCaseImpl: InsertAnnotationUseCaseImpl
    ): InsertAnnotationUseCase

    @Binds
    abstract fun bindRemoveAnnotation(
        removeAnnotationUseCaseImpl: RemoveAnnotationUseCaseImpl
    ): RemoveAnnotationUseCase

    @Binds
    abstract fun bindGetBodyPart(
        getBodyPartListUseCaseImpl: GetBodyPartListUseCaseImpl
    ): GetBodyPartListUseCase

    @Binds
    abstract fun bindGetSelectedExercise(
        getSelectedExercisesUseCaseImpl: GetSelectedExercisesUseCaseImpl
    ): GetSelectedExercisesUseCase

    @Binds
    abstract fun bindGetAllExercises(
        getAllExercises: GetAllExercisesUseCaseImpl
    ): GetAllExercisesUseCase
}