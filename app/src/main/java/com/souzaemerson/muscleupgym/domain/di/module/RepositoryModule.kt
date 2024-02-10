package com.souzaemerson.muscleupgym.domain.di.module

import com.souzaemerson.muscleupgym.data.repository.AnnotationsRepositoryImpl
import com.souzaemerson.muscleupgym.data.repository.BodyPartsRepositoryImpl
import com.souzaemerson.muscleupgym.domain.di.repository.AnnotationsRepository
import com.souzaemerson.muscleupgym.domain.di.repository.BodyPartsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBodyPartsRepository(
        bodyPartsRepositoryImpl: BodyPartsRepositoryImpl
    ): BodyPartsRepository

    @Binds
    abstract fun bindAnnotationsRepository(
        annotationsRepositoryImpl: AnnotationsRepositoryImpl
    ): AnnotationsRepository
}