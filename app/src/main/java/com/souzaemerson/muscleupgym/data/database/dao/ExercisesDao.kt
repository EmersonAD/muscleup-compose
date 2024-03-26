package com.souzaemerson.muscleupgym.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.souzaemerson.muscleupgym.data.model.body.Exercises
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExercise(exercise: Exercises)

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<Exercises>

    @Query("SELECT EXISTS (SELECT 1 FROM exercises WHERE lastTimeSaved IS NOT NULL)")
    suspend fun contains(): Boolean
}