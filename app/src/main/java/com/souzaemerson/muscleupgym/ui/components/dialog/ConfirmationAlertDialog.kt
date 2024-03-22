package com.souzaemerson.muscleupgym.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    leftButtonLabel: String = "Remover",
    rightButtonLabel: String = "Editar",
    onRemove: () -> Unit = {},
    onEdit: @Composable () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier.background(Color.White)
    ) {
        var isEditClicked by remember { mutableStateOf(false) }

        Column {
            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 24.dp, top = 24.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                TextButton(onClick = onRemove) {
                    Text(text = leftButtonLabel, color = Color.Black)
                }
                TextButton(onClick = { isEditClicked = true }) {
                    Text(text = rightButtonLabel, color = Color.Black)
                }
            }
        }

        if (isEditClicked) {
            onEdit()
        }
    }
}

@Composable
@Preview
private fun ConfirmationAlertDialogPreview() {
    GenericAlertDialog(title = "Deseja remover ou editar a anotação selecionada")
}