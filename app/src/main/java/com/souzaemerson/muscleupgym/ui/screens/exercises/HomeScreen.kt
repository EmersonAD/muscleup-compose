package com.souzaemerson.muscleupgym.ui.screens.exercises

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.souzaemerson.muscleupgym.ui.components.CalendarHeader
import com.souzaemerson.muscleupgym.ui.components.item.CategoryItem
import com.souzaemerson.muscleupgym.ui.screens.exercises.viewmodel.HomeViewModel.HomeUiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    onNavigateToExercise: () -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        CalendarHeader(modifier = Modifier)
        Surface(
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxSize(),
            shape = AbsoluteRoundedCornerShape(topLeft = 25.dp, topRight = 25.dp),
            color = Color.Gray,
            border = BorderStroke(width = 2.dp, color = Color.Black)
        ) {
            LazyVerticalGrid(
                modifier = Modifier.padding(12.dp),
                columns = GridCells.Fixed(1),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                when (state) {
                    HomeUiState.Loading -> {}
                    is HomeUiState.Success -> {
                        items(items = state.bodyParts) { category ->
                            CategoryItem(
                                bodyPart = category,
                                onClickCategory = onNavigateToExercise
                            )
                        }
                    }

                    is HomeUiState.Error -> {}
                }

            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun ExercisesScreenPreview() {
//    HomeScreen()
}