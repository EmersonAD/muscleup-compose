package com.souzaemerson.muscleupgym.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderContent(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    gender: String,
    isGenderSelected: Boolean = false,
    onSelected: (genderSelected: Pair<String, Boolean>) -> Unit,
    tint: Color = Color.White
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isGenderSelected) Color.Gray else Color.DarkGray,
            disabledContainerColor = Color.DarkGray
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = {
            onSelected(Pair(gender, !isGenderSelected))
        }
    ) {
        Column(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = imageVector,
                contentDescription = "",
                tint = tint
            )
            Text(text = gender, color = Color.White, fontSize = 16.sp)
        }
    }
}