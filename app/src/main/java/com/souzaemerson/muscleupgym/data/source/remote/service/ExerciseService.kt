package com.souzaemerson.muscleupgym.data.source.remote.service

import retrofit2.http.GET
import retrofit2.http.Path

interface ExerciseService {

    @GET("/exercises/bodyPartList")
    suspend fun getBodyParts(): List<String>

    //    https://exercisedb.p.rapidapi.com/exercises/bodyPart/back?limit=10
    @GET("/exercises/bodyPart/{part}")
    suspend fun getBodyExercise(@Path("part") part: String): List<String>
}