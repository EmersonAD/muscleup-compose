package com.souzaemerson.muscleupgym.ui.components.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    bodyPart: String,
    onClickCategory: () -> Unit = {}
) {
    OutlinedCard(
        modifier = modifier.size(100.dp).padding(4.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        onClick = onClickCategory
    ) {
        val colors = listOf(Color.Black, Color.Transparent, Color.Transparent, Color.Black)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = bodyPart.uppercase(),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Serif
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CategoryItemPreview() {
    CategoryItem(bodyPart = "Back")
}