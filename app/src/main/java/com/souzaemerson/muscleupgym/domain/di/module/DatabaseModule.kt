package com.souzaemerson.muscleupgym.domain.di.module

import android.content.Context
import androidx.room.Room
import com.souzaemerson.muscleupgym.data.database.AppDatabase
import com.souzaemerson.muscleupgym.data.database.Converters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "muscleup.db")
            .build()

    @Provides
    fun provideAnnotationsDao(db: AppDatabase) = db.annotationsDao()
}