package com.souzaemerson.muscleupgym.data.model.annotation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "divisions")
data class Division(
    @PrimaryKey val division: String,
    val annotations: List<Annotation>
)
