package com.souzaemerson.muscleupgym.domain.di.module

import com.souzaemerson.muscleupgym.data.usecase.InsertAnnotationUseCaseImpl
import com.souzaemerson.muscleupgym.data.usecase.RemoveAnnotationUseCaseImpl
import com.souzaemerson.muscleupgym.data.usecase.UpdateAnnotationUseCaseImpl
import com.souzaemerson.muscleupgym.domain.di.usecase.InsertAnnotationUseCase
import com.souzaemerson.muscleupgym.domain.di.usecase.RemoveAnnotationUseCase
import com.souzaemerson.muscleupgym.domain.di.usecase.UpdateAnnotationUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindUpdateAnnotationUseCase(
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

}