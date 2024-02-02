package com.souzaemerson.muscleupgym.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
        modifier = modifier,
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Timer,
                    contentDescription = "Icon Timer",
                    tint = Color.White
                )
            }
        },
        title = {
            Text(
                text = "Muscle Up",
                modifier = Modifier,
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily = FontFamily.Default
                ),
                color = Color.White
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun UpperBarPreview() {
    TopBar()
}