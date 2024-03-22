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
import com.souzaemerson.muscleupgym.ui.components.CreateDivisionBottomSheet
import com.souzaemerson.muscleupgym.ui.components.dialog.CreateAnnotationAlertDialog
import com.souzaemerson.muscleupgym.ui.components.dialog.GenericAlertDialog
import com.souzaemerson.muscleupgym.ui.components.item.AnnotationDivisionItem
import com.souzaemerson.muscleupgym.ui.screens.annotations.util.AnnotationEvent
import com.souzaemerson.muscleupgym.ui.screens.annotations.util.AnnotationState
import com.souzaemerson.muscleupgym.ui.screens.annotations.viewmodel.AnnotationViewModel

@Composable
fun AnnotationScreen(
    modifier: Modifier = Modifier,
    viewModel: AnnotationViewModel,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        val state = viewModel.annotationState

        var currentDivision by remember { mutableStateOf(Division("", emptyList())) }
        var currentAnnotation by remember { mutableStateOf(Annotation("")) }

        if (state.showBottomSheet) {
            CreateDivisionBottomSheet(
                onDismiss = { viewModel.closeBottomSheet() },
                onCreateDivision = { division ->
                    viewModel.onEvent(AnnotationEvent.CreateDivision(division))
                }
            )
        }

        if (state.showCreateAnnotationAlert) {
            CreateAnnotationAlertDialog(
                onComplete = { annotation ->
                    viewModel.onEvent(
                        AnnotationEvent.CreateAnnotation(currentDivision, annotation)
                    )
                },
                onDismiss = {
                    viewModel.closeCreateAnnotationAlert()
                }
            )
        }

        if (state.showDecisionAlert) {

            val selectedDivision = state.divisions.first {
                it.annotations.contains(currentAnnotation)
            }

            ShowDecisionAlertDialog(viewModel, state, currentAnnotation, selectedDivision)
        }

        if (state.showDeleteAnnotationAlert) {
            ShowDeleteAnnotationAlert(viewModel, currentDivision)
        }

        DivisionList(
            divisions = state.divisions,
            addAnnotationIntoDivision = { selectedDivision ->
                currentDivision = selectedDivision
                viewModel.openCreateAnnotationAlert()
            },
            onDelete = {
                viewModel.openDeleteAnnotationAlert()
                currentDivision = it
            },
            onModifyAnnotation = { selectedAnnotation ->
                currentAnnotation = selectedAnnotation
                viewModel.openDecisionAlert()
            }
        )

        FabAdd(modifier = Modifier.align(Alignment.BottomEnd)) {
            viewModel.openBottomSheet()
        }
    }
}

@Composable
private fun ShowDeleteAnnotationAlert(
    viewModel: AnnotationViewModel,
    currentDivision: Division
) {
    GenericAlertDialog(
        title = "Tem certeza que deseja remover a divisão selecionada?",
        onDismissRequest = { viewModel.closeDeleteAnnotationAlert() },
        rightButtonLabel = "Remover",
        leftButtonLabel = "",
        onEdit = {
            viewModel.onEvent(AnnotationEvent.DeleteDivision(currentDivision))
        }
    )
}

@Composable
private fun ShowDecisionAlertDialog(
    viewModel: AnnotationViewModel,
    state: AnnotationState,
    currentAnnotation: Annotation,
    selectedDivision: Division
) {
    GenericAlertDialog(
        title = "Deseja remover ou editar a anotação selecionada?",
        onEdit = {
            viewModel.openUpdateAnnotationAlert()
            if (state.showUpdateAnnotationAlert) {
                CreateAnnotationAlertDialog(
                    annotation = currentAnnotation,
                    onComplete = { annotation ->
                        viewModel.onEvent(
                            AnnotationEvent.UpdateAnnotation(
                                selectedDivision,
                                currentAnnotation,
                                annotation
                            )
                        )
                    },
                    onDismiss = {
                        viewModel.closeUpdateAnnotationAlert()
                        viewModel.closeDecisionAlert()
                    }
                )
            }
        },
        onRemove = {
            viewModel.onEvent(
                AnnotationEvent.RemoveAnnotation(
                    selectedDivision,
                    currentAnnotation
                )
            )

        }, onDismissRequest = {
            viewModel.closeDecisionAlert()
        }
    )
}

@Composable
private fun DivisionList(
    divisions: List<Division>,
    addAnnotationIntoDivision: (division: Division) -> Unit,
    onDelete: (division: Division) -> Unit,
    onModifyAnnotation: (annotation: Annotation) -> Unit
) {
    LazyColumn {
        items(items = divisions) { division ->
            AnnotationDivisionItem(
                modifier = Modifier.padding(bottom = 2.dp),
                division = division,
                onAdd = { addAnnotationIntoDivision(division) },
                onDelete = { onDelete(division) },
                onModifyAnnotation = { annotation ->
                    onModifyAnnotation(annotation)
                }
            )
        }
    }
}

@Composable
fun FabAdd(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.padding(22.dp),
        containerColor = Color.White.copy(alpha = 0.65f)
    ) {
        Icon(imageVector = Icons.Sharp.Add, contentDescription = "Add fab")
    }
}


@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun AnnotationScreenPreview() {
//    AnnotationScreen()
}