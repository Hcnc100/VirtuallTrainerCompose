package com.d34th.nullpointer.virtualtrainercompose.ui.screens.camera

import android.animation.ObjectAnimator
import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.actions.CameraScreenActions
import com.d34th.nullpointer.virtualtrainercompose.actions.CameraScreenActions.*
import com.d34th.nullpointer.virtualtrainercompose.core.states.Resource
import com.d34th.nullpointer.virtualtrainercompose.core.utils.shareViewModel
import com.d34th.nullpointer.virtualtrainercompose.models.Exercise
import com.d34th.nullpointer.virtualtrainercompose.presentation.ExerciseViewModel
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.camera.viewModel.CameraViewModel
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.camera.viewModel.ViewModelFactoryProvider
import com.d34th.nullpointer.virtualtrainercompose.ui.share.ArCoreView
import com.d34th.nullpointer.virtualtrainercompose.ui.share.ToolbarBack
import com.d34th.nullpointer.virtualtrainercompose.ui.states.CameraScreenState
import com.d34th.nullpointer.virtualtrainercompose.ui.states.rememberCameraScreenState
import com.google.ar.sceneform.rendering.ModelRenderable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.EntryPointAccessors

@Destination
@Composable
fun CameraScreen(
    exercise: Exercise,
    navigator: DestinationsNavigator,
    exerciseViewModel: CameraViewModel = measureViewModelProvider(model = exercise.nameModel),
    cameraScreenState: CameraScreenState = rememberCameraScreenState()
) {

    LaunchedEffect(key1 = Unit) {
        exerciseViewModel.messageModels.collect(cameraScreenState::showSnackMessage)
    }



    CameraScreen(
        exercise = exercise,
        scaffoldState = cameraScreenState.scaffoldState,
        modelAnimation = cameraScreenState.modelAnimation,
        isShowExplainDialog = cameraScreenState.isShowDialog,
        isPlayingAnimation = cameraScreenState.isPlayingAnimation,
        callbackAddModelAnimation = cameraScreenState::changeModelAnimation,
        actionCameraScreen = { action ->
            when (action) {
                BACK_ACTION -> navigator.popBackStack()
                PAUSE_OR_PLAY -> cameraScreenState.playPauseAnimation()
                SHOW_DIALOG -> cameraScreenState.changeVisibilityExplainDialog(true)
                HIDDEN_DIALOG -> cameraScreenState.changeVisibilityExplainDialog(false)
            }
        }
    )
}

@Composable
fun CameraScreen(
    exercise: Exercise,
    isPlayingAnimation: Boolean,
    scaffoldState: ScaffoldState,
    modelAnimation: ObjectAnimator?,
    callbackAddModelAnimation: (ObjectAnimator) -> Unit,
    isShowExplainDialog: Boolean,
    actionCameraScreen: (CameraScreenActions) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButtonPosition = FabPosition.Center,
        topBar = {
            ToolbarBack(
                title = stringResource(id = exercise.title),
                actionBack = { actionCameraScreen(BACK_ACTION) }
            )
        },
        floatingActionButton = {
            ButtonPlayPause(
                isPlayingAnimation = isPlayingAnimation,
                playPauseAnimation = { actionCameraScreen(PAUSE_OR_PLAY) }
            )
        }
    ) { paddingValues ->


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            ArCoreView(
                model = exercise.nameModel
            )
            ButtonHelp(
                actionClick = { actionCameraScreen(SHOW_DIALOG) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
            )
            if (isShowExplainDialog)
                DialogExplain(
                    actionHidden = { actionCameraScreen(HIDDEN_DIALOG) },
                    exercise = exercise
                )
        }
    }


}

@Composable
private fun DialogExplain(
    actionHidden: () -> Unit,
    exercise: Exercise
) {
    AlertDialog(
        onDismissRequest = actionHidden,
        confirmButton = {
            TextButton(onClick = actionHidden) {
                Text(text = stringResource(id = R.string.title_accept))
            }
        },
        title = { Text(text = stringResource(R.string.title_description)) },
        text = { Text(text = stringResource(id = exercise.description)) }
    )
}


@Composable
private fun ButtonHelp(
    actionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = actionClick,
        modifier = modifier.size(35.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_question),
            contentDescription = stringResource(
                R.string.description_button_help
            )
        )
    }
}

@Composable
private fun ButtonPlayPause(
    isPlayingAnimation: Boolean,
    modifier: Modifier = Modifier,
    playPauseAnimation: () -> Unit
) {
    val (iconId, descriptionId) = remember(isPlayingAnimation) {
        if (isPlayingAnimation) {
            Pair(R.drawable.ic_stop, R.string.description_pause_animation)
        } else {
            Pair(R.drawable.ic_play, R.string.description_play_animation)
        }
    }
    FloatingActionButton(
        onClick = playPauseAnimation,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = stringResource(id = descriptionId)
        )
    }
}


@Composable
fun measureViewModelProvider(model: String): CameraViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).cameraViewModelFactory()

    return viewModel(factory = CameraViewModel.provideMainViewModelFactory(factory, model))
}



