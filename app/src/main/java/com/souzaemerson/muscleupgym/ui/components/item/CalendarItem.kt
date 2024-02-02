package com.souzaemerson.muscleupgym.ui.components.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.souzaemerson.muscleupgym.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarItem(
    modifier: Modifier = Modifier,
    month: String,
    day: String,
) {
    var isSelected by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier
            .size(60.dp)
            .padding(2.dp),
        shape = ShapeDefaults.ExtraSmall,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {
            isSelected = !isSelected
        }
    ) {
        DateContent(month = month, day = day, isSelected = isSelected)
    }
}

@Composable
fun DateContent(month: String, day: String, isSelected: Boolean) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (isSelected) {
            Image(
                painter = painterResource(id = R.drawable.ic_check_black),
                contentDescription = "",
                modifier = Modifier.padding(12.dp)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = month,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(2.dp)
            )
            Text(
                text = day,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CalendarItemPreview() {
    CalendarItem(month = "111", day = "1")
}