package com.souzaemerson.muscleupgym.data.source.remote.service

import retrofit2.http.GET

interface ExerciseService {
    @GET("/exercises/bodyPartList")
    suspend fun getBodyParts() : List<String>
}