package com.souzaemerson.muscleupgym.ui.screens.imc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.souzaemerson.muscleupgym.data.source.local.bmi.BmiDataSourceImpl
import com.souzaemerson.muscleupgym.ui.components.CardWithButtons
import com.souzaemerson.muscleupgym.ui.components.GenderContent
import com.souzaemerson.muscleupgym.ui.screens.imc.viewmodel.BodyMassIndexViewModel

@Composable
fun BodyMassIndexScreen(modifier: Modifier = Modifier, viewModel: BodyMassIndexViewModel) {

    var height by rememberSaveable { mutableFloatStateOf(175f) }
    var whatGenderIsActive by rememberSaveable { mutableStateOf<String?>("Male") }
    var age by rememberSaveable { mutableIntStateOf(0) }
    var weight by rememberSaveable { mutableDoubleStateOf(0.0) }
    val bmiValue = viewModel.bmi.value

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.background(Color.Black)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                GenderContent(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    imageVector = Icons.Filled.Male,
                    gender = "Male",
                    isGenderSelected = whatGenderIsActive == "Male",
                    onSelected = {
                        whatGenderIsActive = if (it.second) it.first else null
                    }
                )
                GenderContent(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    imageVector = Icons.Filled.Female,
                    gender = "Female",
                    isGenderSelected = whatGenderIsActive == "Female",
                    onSelected = {
                        whatGenderIsActive = if (it.second) it.first else null
                    }
                )
            }

            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.DarkGray
                ),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Slider(
                        modifier = Modifier.padding(4.dp),
                        value = height,
                        onValueChange = { height = it },
                        valueRange = 100f..250f,
                        steps = 0,
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
                        weight = it.toDouble()
                    }
                )
                CardWithButtons(
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp),
                    label = "Age",
                    media = 22,
                    funcCount = {
                        age = it
                    }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                val bmIndex = bmiValue.first
                val formattedIndex = String.format("%.1f", bmIndex)

                if (whatGenderIsActive != null) {
                    viewModel.onEvent(BmiEvent.CalculateBmi(age, height, weight))
                }

                CircularProgressIndicator(
                    progress = bmIndex.toFloat() / 40,
                    modifier = Modifier.size(100.dp),
                    strokeWidth = 20.dp,
                    strokeCap = StrokeCap.Butt,
                    color = Color.DarkGray,
                    trackColor = setColorByIndex(bmiValue.second),
                )

                Text(
                    text = formattedIndex,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = bmiValue.second,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Light,
            )
        }
    }
}

private fun setColorByIndex(bmIndex: String) = when(bmIndex) {
    "Abaixo do peso" -> Color.Yellow
    "Peso normal" -> Color.Green
    "Acima do peso" -> Color.Red.copy(red = 1f, green = 0.6f)
    "Sobrepeso" -> Color.Red.copy(red = 1f, green = 0.5f)
    "Obesidade grau I" -> Color.Red
    else -> Color.Red
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun BodyMassIndexScreenPreview() {
    BodyMassIndexScreen(viewModel = BodyMassIndexViewModel(BmiDataSourceImpl()))
}