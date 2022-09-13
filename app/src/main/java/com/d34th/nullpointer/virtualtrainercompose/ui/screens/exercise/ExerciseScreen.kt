package com.d34th.nullpointer.virtualtrainercompose.ui.screens.exercise

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.d34th.nullpointer.virtualtrainercompose.presentation.ExerciseViewModel
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.exercise.componets.ExerciseItem
import com.d34th.nullpointer.virtualtrainercompose.ui.share.SimpleToolbar

@Composable
fun ExercisesScreen(
    exerciseViewModel: ExerciseViewModel = hiltViewModel()
) {
    Scaffold(topBar = { SimpleToolbar(title = "Lista de ejercicios") }) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(250.dp),
            modifier = Modifier.padding(it),
        ) {
            items(exerciseViewModel.listExercise) { exercise ->
                ExerciseItem(
                    exercise = exercise,
                    onClickExercise = { exercise })
            }
        }
    }
}