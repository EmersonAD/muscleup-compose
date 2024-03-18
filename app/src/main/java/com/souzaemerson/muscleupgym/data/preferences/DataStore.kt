package com.souzaemerson.muscleupgym.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStore @Inject constructor(@ApplicationContext private val context: Context) {

    private val gson = Gson()

    suspend fun <T> setList(key: Preferences.Key<String>, content: List<T>) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[key] = gson.toJson(content)
        }
    }

    fun getList(key: Preferences.Key<String>): Flow<List<String>> {
        return context.dataStore.data.map { preferences ->
            gson.fromJson(preferences[key], object : TypeToken<List<String>>() {}.type)
        }
    }

    suspend fun <T> setObject(key: Preferences.Key<String>, obj: T) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[key] = gson.toJson(obj)
        }
    }

    fun <T> getObject(key: Preferences.Key<String>, clazz: Class<T>): Flow<T> {
        return context.dataStore.data.map { preferences ->
            gson.fromJson(preferences[key] ?: "", clazz)
        }
    }

    fun exists(key: Preferences.Key<String>): Flow<Boolean> {
        return context.dataStore.data.map {
            it.contains(key)
        }
    }
}