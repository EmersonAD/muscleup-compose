package com.souzaemerson.muscleupgym.data.model.annotation

data class Annotation(
    val exercise: String,
    val weight: Int? = null,
    val plates: Int? = null
) {
    fun getCurrentType() = weight?.let { "Weight" } ?: plates?.let { "Plates" } ?: ""
    fun getCurrentWeightOrPlates() =
        if (weight == null && plates == null) {
            ""
        } else if (plates == null) {
            "$weight"
        } else {
            "$plates"
        }

}
