package com.souzaemerson.muscleupgym.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import kotlinx.coroutines.flow.Flow

@Dao
interface AnnotationDao {

    @Query("SELECT * FROM divisions")
    fun getAllDivisions(): Flow<List<Division>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDivision(division: Division)

    @Transaction
    suspend fun insertAnnotationIntoDivision(division: Division, annotation: Annotation) {
        val updatedAnnotations = division.annotations.toMutableList().also {
            it.add(annotation)
        }
        val updatedDivision = division.copy(
            annotations = updatedAnnotations
        )
        updateDivision(updatedDivision)
    }

    @Update
    suspend fun updateDivision(division: Division)

    @Delete
    suspend fun delete(division: Division)
}