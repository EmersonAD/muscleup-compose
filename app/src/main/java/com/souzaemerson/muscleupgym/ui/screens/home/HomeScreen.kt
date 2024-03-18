package com.souzaemerson.muscleupgym.ui.screens.home

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.souzaemerson.muscleupgym.ui.components.CalendarHeader
import com.souzaemerson.muscleupgym.ui.components.item.DivisionItem
import com.souzaemerson.muscleupgym.ui.extensions.navigateTo
import com.souzaemerson.muscleupgym.ui.screens.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHost: NavHostController,
    viewModel: HomeViewModel
) {
    Box(
        modifier = modifier
    ) {

        val state = viewModel.state.collectAsState()

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
                items(items = state.value.bodyParts) { bodyPart ->
                    DivisionItem(bodyPart = bodyPart) {
                        navHost.navigateTo("exercises/${it.lowercase()}")
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun HomeScreenPreview() {
//    HomeScreen()
}