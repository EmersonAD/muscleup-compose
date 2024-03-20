package com.souzaemerson.muscleupgym.ui.components.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.souzaemerson.muscleupgym.R
import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ExerciseItem(
    modifier: Modifier = Modifier,
    bodyPartEntity: BodyPartEntity,
    onClick: (item: BodyPartEntity) -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = { onClick(bodyPartEntity) }
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            GlideImage(
                model = bodyPartEntity.gifUrl,
                contentDescription = "Image content",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
            ) {
                it.dontAnimate()
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                    text = bodyPartEntity.name.replaceFirstChar { it.uppercase() },
                    fontWeight = FontWeight.W400,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    softWrap = true
                )
                Text(
                    text = bodyPartEntity.target.replaceFirstChar { it.uppercase() },
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun ExerciseItemPreview() {
    ExerciseItem(
        bodyPartEntity = BodyPartEntity(
            "Costas",
            "",
            "https://v2.exercisedb.io/image/Zfv8AL11Qpde0F",
            "",
            emptyList(),
            "Remada curvada",
            emptyList(),
            ""
        )
    )
}