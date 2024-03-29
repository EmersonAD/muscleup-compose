package com.souzaemerson.muscleupgym.ui.components.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division

@Composable
fun AnnotationDivisionItem(
    modifier: Modifier = Modifier,
    division: Division,
    onAdd: () -> Unit,
    onDelete: () -> Unit,
    onModifyAnnotation: (annotation: Annotation) -> Unit
) {

    Column(
        modifier = modifier
            .background(Color.Black)
            .padding()
            .fillMaxWidth()
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 2.dp)
                    .weight(1f),
                text = "Target: ${division.division.replaceFirstChar { it.uppercase() }}",
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                color = Color.White,
            )

            IconButton(onClick = {
                onAdd()
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    "Add button",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(onClick = {
                onDelete.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.DeleteForever,
                    "Exclude button",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Column {
            division.annotations.forEach { annotation: Annotation ->
                AnnotationContent(
                    exercise = annotation.exercise,
                    weight = annotation.weight,
                    plates = annotation.plates,
                    onLongClick = { onModifyAnnotation(annotation) }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnnotationContent(exercise: String, weight: Int?, plates: Int?, onLongClick: () -> Unit) {
    Column(
        modifier = Modifier
            .combinedClickable(
                onClick = {},
                onLongClick = onLongClick
            )
            .background(Color.Black)
    ) {
        HorizontalDivider(color = Color.White.copy(0.2f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = exercise.replaceFirstChar { it.uppercase() }, fontWeight = FontWeight.Light,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 6.dp),
            )
            Text(
                text = weight?.let { "Weight: $weight Kg" } ?: "Plates: $plates",
                modifier = Modifier.padding(end = 8.dp),
                color = Color.White,
                fontWeight = FontWeight.Light
            )
        }
        HorizontalDivider(color = Color.White.copy(0.1f))
    }
}

@Composable
@Preview
private fun AnnotationCategoryItemPreview() {
    AnnotationDivisionItem(
        division = Division("Test", emptyList()),
        onAdd = {},
        onDelete = {},
        onModifyAnnotation = {}
    )
}