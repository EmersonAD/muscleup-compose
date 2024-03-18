package com.souzaemerson.muscleupgym.data.source.remote.service

import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ExerciseService {

    @GET("/exercises/bodyPartList")
    suspend fun getBodyPartList(): List<String>

    @GET("/exercises")
    suspend fun getBodyExercise(
        @Query("limit") limit: Int
    ): List<BodyPartEntity>
}