package com.souzaemerson.muscleupgym.data.model

data class Annotation(
    val category: String,
    val name: String,
    val weight: List<String>,
    val plates: Int?
) {
    override fun toString(): String {
        return "Aqui estão suas ultimas pesagens: ${weight.joinToString()}"
    }
}
