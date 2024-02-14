package com.souzaemerson.muscleupgym.ui.screens.annotations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.souzaemerson.muscleupgym.data.model.annotation.Annotation
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import com.souzaemerson.muscleupgym.ui.components.AnnotationAlertDialog
import com.souzaemerson.muscleupgym.ui.components.AnnotationBottomSheet
import com.souzaemerson.muscleupgym.ui.components.item.AnnotationDivisionItem
import com.souzaemerson.muscleupgym.ui.screens.annotations.viewmodel.AnnotationViewModel

@Composable
fun AnnotationScreen(
    modifier: Modifier = Modifier,
    viewModel: AnnotationViewModel,
) {
    val state = viewModel.annotations.value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        var showBottomSheet by remember { mutableStateOf(false) }
        var showAlertDialog by remember { mutableStateOf(false) }
        var division by remember { mutableStateOf(Division("", listOf())) }

        if (showBottomSheet) {
            AnnotationBottomSheet(onDismiss = {
                showBottomSheet = false
            }, onCreateDivision = {
                viewModel.onEvent(DivisionEvent.AddDivision(it))
                showBottomSheet = false
            })
        }

        if (showAlertDialog) {
            UpdateAnnotation(
                currentDivision = division,
                onDismiss = { showAlertDialog = false },
                viewModel = viewModel
            )
        }

        DivisionList(
            state.divisions,
            onAddDivision = { currentDivision ->
                division = currentDivision
                showAlertDialog = true
            },
            onDelete = { viewModel.onEvent(DivisionEvent.DeleteDivision(it)) }
        )

        FabAdd(modifier = Modifier.align(Alignment.BottomEnd)) {
            showBottomSheet = true
        }
    }
}

@Composable
private fun UpdateAnnotation(
    currentDivision: Division,
    onDismiss: () -> Unit,
    viewModel: AnnotationViewModel
) {
    AnnotationAlertDialog(
        onComplete = { annotation ->
            val division = currentDivision.copy(
                division = currentDivision.division,
                annotations = currentDivision.annotations.toMutableList().also {
                    it.add(
                        Annotation(
                            exercise = annotation.exercise,
                            weight = annotation.weight,
                            plates = annotation.plates
                        )
                    )
                }
            )
            viewModel.onEvent(DivisionEvent.CreateAnnotations(division))
            onDismiss()
        }, onDismiss = {
            onDismiss()
        }
    )
}

@Composable
private fun DivisionList(
    state: List<Division>,
    onAddDivision: (division: Division) -> Unit,
    onDelete: (division: Division) -> Unit
) {
    LazyColumn {
        items(items = state) {
            AnnotationDivisionItem(
                modifier = Modifier.padding(1.dp),
                division = it,
                onAdd = {
                    onAddDivision(it)
                },
                onDelete = { onDelete(it) }
            )
        }
    }
}

@Composable
fun FabAdd(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .padding(22.dp),
        containerColor = Color.White.copy(alpha = 0.9f)
    ) {
        Icon(imageVector = Icons.Sharp.Add, contentDescription = "Add fab")
    }
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun AnnotationScreenPreview() {
//    AnnotationScreen()
}