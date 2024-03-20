package com.souzaemerson.muscleupgym.ui.screens.exercise

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.souzaemerson.muscleupgym.data.model.body.BodyPartEntity
import com.souzaemerson.muscleupgym.ui.components.item.ExerciseItem
import com.souzaemerson.muscleupgym.ui.screens.exercise.viewmodel.ExercisesViewModel

@Composable
fun ExercisesScreen(
    modifier: Modifier = Modifier,
    viewModel: ExercisesViewModel,
    bodyPartSelected: String,
    navHostController: NavHostController
) {

    val state = viewModel.exercise.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var exerciseOpened by remember { mutableStateOf<BodyPartEntity?>(null) }

    LaunchedEffect(true) {
        viewModel.getSelectedExercises(bodyPartSelected)
    }

    Scaffold(modifier = modifier.fillMaxSize(), containerColor = Color.DarkGray, topBar = {
        ExercisesTopBar(
            if (state.value.isLoading) "" else state.value.exercises.first().bodyPart,
            onBack = { navHostController.popBackStack() },
        )
    }) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(state.value.exercises) { exercise ->
                ExerciseItem(
                    modifier = Modifier.padding(4.dp),
                    bodyPartEntity = exercise
                ) { selectedExercise ->
                    showBottomSheet = true
                    exerciseOpened = selectedExercise
                }
            }
        }

        if (showBottomSheet) {
            exerciseOpened?.let { exercise ->
                ExerciseDetailsBottomSheet(
                    onDismiss = { showBottomSheet = false },
                    exercise = exercise
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalGlideComposeApi::class
)
@Composable
private fun ExerciseDetailsBottomSheet(
    modifier: Modifier = Modifier,
    exercise: BodyPartEntity,
    onDismiss: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { exercise.instructions.size })

    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss,
        containerColor = Color.White
    ) {
        Box(modifier = Modifier.padding(bottom = 24.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                GlideImage(model = exercise.gifUrl, contentDescription = "Exercise gif")

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = exercise.name.replaceFirstChar { it.uppercase() },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400
                )

                HorizontalPager(state = pagerState) { index ->
                    val instruction = exercise.instructions[index]
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = instruction,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Light
                    )
                }

                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    pageCount = exercise.instructions.size
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ExercisesTopBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title.uppercase(),
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                color = Color.White,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
        navigationIcon = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        })
}