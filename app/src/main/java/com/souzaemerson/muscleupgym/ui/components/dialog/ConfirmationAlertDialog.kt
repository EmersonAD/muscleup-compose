package com.souzaemerson.muscleupgym.ui.components.dialog

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun ConfirmationAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    onDismissRequest: () -> Unit,
    onConfirm: @Composable () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(
            topStart = 0f,
            topEnd = 0f,
            bottomStart = 0f,
            bottomEnd = 0f
        ),
        confirmButton = { onConfirm() },
        title = { Text(text = title, fontSize = 14.sp) },
    )
}