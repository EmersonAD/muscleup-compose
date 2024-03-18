package com.souzaemerson.muscleupgym.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import java.lang.reflect.Type
import java.util.Collections

class Converters {
    @TypeConverter
    fun fromDivision(division: Division): String =
        Gson().toJson(division)

    @TypeConverter
    fun toDivision(string: String): Division =
        Gson().fromJson(string, Division::class.java)

    @TypeConverter
    fun fromBodyEntity(bodyPartEntity: BodyPartEntity): String =
        Gson().toJson(bodyPartEntity)

    @TypeConverter
    fun toBodyEntity(string: String): BodyPartEntity =
        Gson().fromJson(string, BodyPartEntity::class.java)

    @TypeConverter
    fun fromAnnotation(annotation: Annotation): String =
        Gson().toJson(annotation)

    @TypeConverter
    fun toAnnotation(string: String): Annotation =
        Gson().fromJson(string, Annotation::class.java)

    @TypeConverter
    fun fromList(list: List<Annotation>): String =
        Gson().toJson(list)

    @TypeConverter
    fun toList(string: String?): List<Annotation> {
        if (string == null){
           return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Annotation>?>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun fromListBody(list: List<BodyPartEntity>): String =
        Gson().toJson(list)

    @TypeConverter
    fun toListBody(string: String?): List<BodyPartEntity> {
        if (string == null){
           return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<BodyPartEntity>?>() {}.type
        return Gson().fromJson(string, listType)
    }

    @TypeConverter
    fun fromListString(list: List<String>): String =
        Gson().toJson(list)

    @TypeConverter
    fun toListString(string: String?): List<String> {
        if (string == null){
           return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<String>?>() {}.type
        return Gson().fromJson(string, listType)
    }

}