package com.souzaemerson.muscleupgym.ui.screens.imc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.souzaemerson.muscleupgym.data.source.local.bmi.BmiDataSource
import com.souzaemerson.muscleupgym.ui.components.CardWithButtons
import com.souzaemerson.muscleupgym.ui.components.GenderContent
import com.souzaemerson.muscleupgym.ui.screens.imc.viewmodel.BodyMassIndexViewModel

@Composable
fun BodyMassIndexScreen(modifier: Modifier = Modifier, viewModel: BodyMassIndexViewModel) {

    var height by rememberSaveable { mutableFloatStateOf(175f) }
    var whichGenderIsActive by rememberSaveable { mutableStateOf<String?>(null) }
    var age by rememberSaveable { mutableIntStateOf(0) }
    var weight by rememberSaveable { mutableDoubleStateOf(0.0) }
    val bmiValue = viewModel.bmi.value

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        whichGenderIsActive?.let { viewModel.onEvent(BmiEvent.CalculateBmi(age, height, weight)) }

        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {

            GendersContent(
                whichGenderIsActive = whichGenderIsActive,
                maleSelected = { selectedGender ->
                    whichGenderIsActive = if (selectedGender.second) selectedGender.first else null
                }, femaleSelected = { selectedGender ->
                    whichGenderIsActive = if (selectedGender.second) selectedGender.first else null
                }
            )

            SliderHeightCard(
                height = height,
                onValueChange = { height = it },
                whichGenderIsActive = whichGenderIsActive
            )

            InformationCards(weight = { weight = it }, age = { age = it })

            BmiResultCard(index = bmiValue.first.run {
                String.format("%.1f", this)
            }.toString(), bmiCategory = bmiValue.second)
        }
    }
}

@Composable
private fun GendersContent(
    modifier: Modifier = Modifier,
    whichGenderIsActive: String?,
    maleSelected: (genderSelected: Pair<String, Boolean>) -> Unit,
    femaleSelected: (genderSelected: Pair<String, Boolean>) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        GenderContent(
            modifier = Modifier
                .weight(1f)
                .padding(2.dp),
            imageVector = Icons.Filled.Male,
            gender = "Male",
            isGenderSelected = whichGenderIsActive == "Male",
            onSelected = {
                maleSelected(it)
            }
        )

        GenderContent(
            modifier = Modifier
                .weight(1f)
                .padding(2.dp),
            imageVector = Icons.Filled.Female,
            gender = "Female",
            isGenderSelected = whichGenderIsActive == "Female",
            onSelected = {
                femaleSelected(it)
            }
        )
    }
}

@Composable
private fun SliderHeightCard(
    modifier: Modifier = Modifier,
    height: Float,
    onValueChange: (height: Float) -> Unit,
    whichGenderIsActive: String?
) {

    Card(
        modifier = modifier.padding(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Slider(
                modifier = Modifier.padding(4.dp),
                value = height,
                onValueChange = {
                    onValueChange(it)
                },
                valueRange = 100f..250f,
                steps = 0,
                enabled = whichGenderIsActive?.let { true } ?: false,
                colors = SliderDefaults.colors(
                    activeTrackColor = Color.Black,
                    thumbColor = Color.Gray
                )
            )
            Text(text = "${height.toInt()} cm", color = Color.White, fontSize = 20.sp)
            Text(
                text = "Height",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Light,
            )
        }
    }

}

@Composable
private fun InformationCards(weight: (weight: Double) -> Unit, age: (age: Int) -> Unit) {

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        CardWithButtons(
            modifier = Modifier
                .weight(1f)
                .padding(2.dp),
            label = "Weight",
            media = 60,
            funcCount = {
                weight(it.toDouble())
            }
        )
        CardWithButtons(
            modifier = Modifier
                .weight(1f)
                .padding(2.dp),
            label = "Age",
            media = 22,
            funcCount = {
                age(it)
            }
        )
    }
}

@Composable
private fun BmiResultCard(modifier: Modifier = Modifier, index: String, bmiCategory: String) {
    Card(
        modifier = modifier.padding(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = index, fontSize = 26.sp, fontWeight = FontWeight.W700, color = Color.White)
            Text(
                text = bmiCategory.ifBlank { "BMI Result" },
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                color = Color.White
            )
        }

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun BodyMassIndexScreenPreview() {
    BodyMassIndexScreen(viewModel = BodyMassIndexViewModel(BmiDataSource()))
}