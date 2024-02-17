package com.souzaemerson.muscleupgym.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardWithButtons(
    modifier: Modifier = Modifier,
    label: String,
    media: Int = 30,
    funcCount: (weight: Int) -> Unit
) {

    var count by rememberSaveable { mutableIntStateOf(media) }
    funcCount(count)

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = label,
                modifier = Modifier.padding(top = 4.dp),
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Light
            )
            Text(
                text = count.toString(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 2.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            ) {
                IconButton(
                    onClick = {
                        if (count > 0) {
                            count--
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Gray
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Remove,
                        contentDescription = "Minus count button",
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = {
                        count++
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Gray
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Plus count button",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun CardWithButtonsPreview() {
    CardWithButtons(label = "Weight", funcCount = {})
}