package com.souzaemerson.muscleupgym.data.source.local.bmi

class BmiDataSource() {

    fun getBodyMassIndex(age: Int, height: Float, weight: Double): Pair<Double, String> {
        val correctHeight = height/100
        val bmi = weight / (correctHeight * correctHeight)
        return getBmiClassification(age, bmi)
    }

    private fun getBmiClassification(age: Int, bmi: Double): Pair<Double, String> {
        val bmiRanges = if (age >= 60) {
            listOf(
                0.0 to "Ajuste seus dados",
                23.0 to "Abaixo do peso",
                28.0 to "Peso normal",
                30.0 to "Sobrepeso",
                Double.MAX_VALUE to "Obesidade"
            )
        } else {
            listOf(
                0.0 to "Ajuste seus dados",
                18.5 to "Abaixo do peso",
                24.9 to "Peso normal",
                29.9 to "Sobrepeso",
                34.9 to "Obesidade grau I",
                39.9 to "Obesidade grau II",
                Double.MAX_VALUE to "Obesidade grau III"
            )
        }

        val classification = bmiRanges.first {
            bmi < it.first
        }.second

        return Pair(bmi, classification)
    }
}
