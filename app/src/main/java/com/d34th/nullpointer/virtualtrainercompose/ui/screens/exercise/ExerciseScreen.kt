package com.d34th.nullpointer.virtualtrainercompose.ui.screens.exercise

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.core.utils.shareViewModel
import com.d34th.nullpointer.virtualtrainercompose.models.Exercise
import com.d34th.nullpointer.virtualtrainercompose.presentation.ExerciseViewModel
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.destinations.CameraScreenDestination
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.destinations.DataUserScreenDestination
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.exercise.componets.ExerciseItem
import com.d34th.nullpointer.virtualtrainercompose.ui.share.SimpleToolbar
import com.d34th.nullpointer.virtualtrainercompose.ui.share.ToolbarSettings
import com.d34th.nullpointer.virtualtrainercompose.ui.states.SimpleScreenState
import com.d34th.nullpointer.virtualtrainercompose.ui.states.rememberSimpleScreenState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination(start = true)
@Composable
fun ExercisesScreen(
    navigator: DestinationsNavigator,
    exerciseViewModel: ExerciseViewModel = shareViewModel(),
    exerciseScreenState: SimpleScreenState = rememberSimpleScreenState()
) {

    val listExercise = exerciseViewModel.listExercise

    ListExercises(
        listExercise = listExercise,
        scaffoldState = exerciseScreenState.scaffoldState,
        actionClickModel = {
            navigator.navigate(CameraScreenDestination(it))
        }
    )
}

@Composable
private fun ListExercises(
    listExercise: List<Exercise>,
    scaffoldState: ScaffoldState,
    actionClickModel: (Exercise) -> Unit
) {

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleToolbar(
                title = stringResource(id = R.string.app_name)
            )
        }) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(250.dp),
            modifier = Modifier.padding(paddingValues),
        ) {
            items(
                listExercise,
                key = { it.nameModel }
            ) { exercise ->
                ExerciseItem(
                    exercise = exercise,
                    onClickExercise = { actionClickModel(exercise) }
                )
            }
        }
    }


}