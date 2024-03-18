package com.souzaemerson.muscleupgym.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.souzaemerson.muscleupgym.data.database.dao.AnnotationDao
import com.souzaemerson.muscleupgym.data.database.dao.ExercisesDao
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity

@Database(entities = [Division::class, BodyPartEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun annotationsDao(): AnnotationDao
    abstract fun exercisesDao(): ExercisesDao
}