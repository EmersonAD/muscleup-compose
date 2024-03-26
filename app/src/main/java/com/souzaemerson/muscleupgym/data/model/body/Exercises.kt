package com.souzaemerson.muscleupgym.data.model.body

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercises (
    @PrimaryKey val lastTimeSaved: String,
    val exercises: List<BodyPartEntity>
)