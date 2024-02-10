package com.souzaemerson.muscleupgym.ui.screens.annotations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.souzaemerson.muscleupgym.core.State
import com.souzaemerson.muscleupgym.core.Status
import com.souzaemerson.muscleupgym.data.model.annotation.Division
import com.souzaemerson.muscleupgym.ui.components.AnnotationBottomSheet
import com.souzaemerson.muscleupgym.ui.components.item.AnnotationDivisionItem
import com.souzaemerson.muscleupgym.ui.screens.annotations.viewmodel.AnnotationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun AnnotationScreen(
    modifier: Modifier = Modifier,
    viewModel: AnnotationViewModel,
) {
    val state = viewModel.annotations.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        var showBottomSheet by remember { mutableStateOf(false) }

        if (showBottomSheet) {
            AnnotationBottomSheet(onDismiss = {
                showBottomSheet = false
            }, onCreateDivision = {
                viewModel.createAnnotationDivision(it)
                showBottomSheet = false
            })
        }

        DivisionList(
            state.value,
            onAdd = {},
            onDelete = { viewModel.deleteAnnotationDivision(it) }
        )

        FabAdd(modifier = Modifier.align(Alignment.BottomEnd)) {
            showBottomSheet = true
        }
    }
}

@Composable
private fun DivisionList(
    state: List<Division>,
    onAdd: (division: Division) -> Unit,
    onDelete: (division: Division) -> Unit
) {
    LazyColumn {
        items(items = state) {
            AnnotationDivisionItem(
                division = it,
                onAdd = {},
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