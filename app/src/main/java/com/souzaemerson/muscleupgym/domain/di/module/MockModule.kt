package com.souzaemerson.muscleupgym.domain.di.module

import android.content.Context
import com.souzaemerson.muscleupgym.data.util.MockUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class MockModule {

    @Provides
    fun provideMockUtils(@ApplicationContext context: Context) = MockUtils(context)
}