package com.souzaemerson.muscleupgym.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.souzaemerson.muscleupgym.data.database.dao.AnnotationDao
import com.souzaemerson.muscleupgym.data.model.annotation.Division

@Database(entities = [Division::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun annotationsDao(): AnnotationDao
}