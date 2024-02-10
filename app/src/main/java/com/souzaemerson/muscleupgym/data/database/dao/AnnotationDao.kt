package com.souzaemerson.muscleupgym.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import kotlinx.coroutines.flow.Flow

@Dao
interface AnnotationDao {

    @Query("SELECT * FROM divisions")
    fun getAllDivisions(): Flow<List<Division>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDivision(division: Division)

    @Update
    suspend fun addAnnotationIntoDivision(division: Division)

    @Delete
    suspend fun delete(division: Division)
}