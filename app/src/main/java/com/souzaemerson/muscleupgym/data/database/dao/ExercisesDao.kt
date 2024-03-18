package com.souzaemerson.muscleupgym.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExercise(exercise: BodyPartEntity)

    @Query("SELECT * FROM exercises")
    fun getAllExercises(): Flow<List<BodyPartEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM exercises WHERE target = :target)")
    suspend fun exists(target: String = "abs"): Boolean
}