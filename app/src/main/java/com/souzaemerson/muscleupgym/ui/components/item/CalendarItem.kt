package com.souzaemerson.muscleupgym.ui.components.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalendarItem(
    modifier: Modifier = Modifier,
    month: String,
    day: String,
    nameOfDay: String,
    color: Color = Color.White
) {
    Card(
        modifier = modifier
            .size(60.dp)
            .padding(2.dp),
        shape = ShapeDefaults.ExtraSmall,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(containerColor = color),
    ) {
        DateContent(month = month, day = day, nameOfDay = nameOfDay)
    }
}

@Composable
fun DateContent(month: String, day: String, nameOfDay: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.offset(y = (-15).dp),
            text = month,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
        )
        Text(
            text = day,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            modifier = Modifier.offset(y = 17.dp),
            text = nameOfDay,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraLight,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CalendarItemPreview() {
    CalendarItem(month = "111", day = "1", nameOfDay = "Tue")
}