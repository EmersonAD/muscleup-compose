package com.souzaemerson.muscleupgym.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.souzaemerson.muscleupgym.data.model.annotation.Division

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnotationBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onCreateDivision: (Division) -> Unit
) {
    var divisionText by remember { mutableStateOf("") }

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss,
        containerColor = Color.White,
    ) {
        Column {

            Text(
                text = "Escreva o grupo muscular referente as anotações:",
                color = Color.Gray,
                modifier = Modifier.padding(start = 8.dp)
            )

            OutlinedTextField(
                value = divisionText,
                onValueChange = { divisionText = it },
                label = { Text(text = "Grupo Muscular") },
                singleLine = true,
                supportingText = {
                    Text(
                        "Exemplo: Costas, Braço, Perna, etc...",
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )

            Text(
                text = "Criar divisão".uppercase(),
                color = if (divisionText.isNotBlank()) Color.Black else Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.W300,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 8.dp, bottom = 18.dp)
                    .clickable {
                        onCreateDivision(Division(divisionText, annotations = emptyList()))
                    }
            )
        }
    }
}
