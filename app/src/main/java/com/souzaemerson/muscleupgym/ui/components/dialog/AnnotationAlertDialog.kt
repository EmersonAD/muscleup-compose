package com.souzaemerson.muscleupgym.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.souzaemerson.muscleupgym.data.model.annotation.Annotation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAnnotationAlertDialog(
    modifier: Modifier = Modifier,
    onComplete: (Annotation) -> Unit = {},
    onDismiss: () -> Unit,
    exercise: String = "",
    type: String = "",
    weight: String = ""
) {
    var exerciseText by remember { mutableStateOf(exercise) }
    var typeText by remember { mutableStateOf(type) }
    var weightText by remember { mutableStateOf(weight) }
    var isExpanded by remember { mutableStateOf(false) }
    val isComplete = exerciseText.isNotBlank() && typeText.isNotBlank() && weightText.isNotBlank()

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        properties = DialogProperties(),
    ) {
        Box(modifier = Modifier.background(Color.White)) {
            Column {

                Text(
                    text = "Write your exercise",
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 4.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp),
                    value = exerciseText,
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                    ),
                    onValueChange = { exerciseText = it },
                    label = { Text(text = "Exercise", color = Color.DarkGray.copy(alpha = 0.8f)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                Row(modifier = Modifier.padding(4.dp)) {

                    OutlinedTextField(
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1f),
                        value = typeText,
                        onValueChange = { typeText = it },
                        readOnly = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                        ),
                        label = {
                            Text(
                                text = "Type",
                                fontSize = 14.sp,
                                color = Color.DarkGray.copy(alpha = 0.8f)
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.ExpandMore,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    isExpanded = !isExpanded
                                }
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false },
                        offset = DpOffset(x = 0.dp, y = 0.dp)
                    ) {
                        DropdownMenuItem(text = { Text("Plates") }, onClick = {
                            isExpanded = false
                            typeText = "Plates"
                        })
                        DropdownMenuItem(text = { Text(text = "Weight") }, onClick = {
                            isExpanded = false
                            typeText = "Weight"
                        })
                    }

                    OutlinedTextField(
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(1.4f),
                        value = weightText,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                        ),
                        singleLine = true,
                        onValueChange = { weightText = it },
                        label = {
                            Text(
                                text = "Weight/Count",
                                color = Color.DarkGray.copy(alpha = 0.8f)
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )

                }
                Text(
                    text = "FINISH",
                    modifier = Modifier
                        .padding(end = 16.dp, top = 4.dp, bottom = 12.dp)
                        .clickable(enabled = isComplete, role = Role.Button) {
                            onComplete(setAnnotationByType(typeText, exerciseText, weightText))
                        }
                        .align(AbsoluteAlignment.Right),
                    fontSize = 16.sp,
                    color = if (isComplete) Color.Black else Color.Gray,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

private fun setAnnotationByType(
    typeText: String,
    exercise: String,
    weight: String
): Annotation {
    return if (typeText == "Weight") {
        Annotation(exercise, weight.toInt())
    } else Annotation(exercise, plates = weight.toInt())
}

@Composable
@Preview(showBackground = true, widthDp = 300)
private fun AnnotationAlertDialogPreview() {
//    AnnotationAlertDialog()
}