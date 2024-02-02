package com.souzaemerson.muscleupgym.ui.components.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ExerciseItem(
    image: String,
    type: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black)
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
            Text(
                text = type.uppercase(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ExerciseItemPreview() {
    ExerciseItem(image = "", type = "")
}