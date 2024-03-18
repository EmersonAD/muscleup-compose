package com.souzaemerson.muscleupgym.data.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import javax.inject.Inject

class MockUtils @Inject constructor(private val context: Context) {

    fun getMockFromAsset(fileName: String): List<BodyPartEntity>? {
        loadJsonFromAssets("mock/$fileName")?.let { mockJson ->
            return Gson().fromJson<List<BodyPartEntity>>(mockJson, object : TypeToken<List<BodyPartEntity>?>() {}.type)
        }
        return emptyList()
    }

    private fun loadJsonFromAssets(fileName: String) = try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (e: Exception) {
        null
    }
}