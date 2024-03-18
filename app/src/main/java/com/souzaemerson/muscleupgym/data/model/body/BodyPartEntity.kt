package com.souzaemerson.muscleupgym.data.model.body

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "exercises", primaryKeys = ["id"])
data class BodyPartEntity(
    @SerializedName("bodyPart") val bodyPart: String,
    @SerializedName("equipment") val equipment: String,
    @SerializedName("gifUrl") val gifUrl: String,
    @SerializedName("id") val id: String,
    @SerializedName("instructions") val instructions: List<String>,
    @SerializedName("name") val name: String,
    @SerializedName("secondaryMuscles") val secondaryMuscles: List<String>,
    @SerializedName("target") val target: String
) : Parcelable