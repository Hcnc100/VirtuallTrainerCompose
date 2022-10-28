package com.d34th.nullpointer.virtualtrainercompose.ui.screens.camera

import android.animation.ObjectAnimator
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.d34th.nullpointer.virtualtrainercompose.core.states.Resource
import com.d34th.nullpointer.virtualtrainercompose.core.utils.shareViewModel
import com.d34th.nullpointer.virtualtrainercompose.presentation.ExerciseViewModel
import com.d34th.nullpointer.virtualtrainercompose.ui.share.ArCoreView
import com.d34th.nullpointer.virtualtrainercompose.ui.share.ToolbarBack
import com.d34th.nullpointer.virtualtrainercompose.ui.states.CameraScreenState
import com.d34th.nullpointer.virtualtrainercompose.ui.states.rememberCameraScreenState
import com.google.ar.sceneform.rendering.ModelRenderable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CameraScreen(
    navigator: DestinationsNavigator,
    exerciseViewModel: ExerciseViewModel = shareViewModel(),
    cameraScreenState: CameraScreenState = rememberCameraScreenState()
) {

    LaunchedEffect(key1 = Unit) {
        exerciseViewModel.messageModels.collect(cameraScreenState::showSnackMessage)
    }

    val stateModel by exerciseViewModel.modelRenderable.collectAsState()

    CameraScreen(
        stateModel = stateModel,
        actionBack = navigator::popBackStack,
        scaffoldState = cameraScreenState.scaffoldState,
        modelAnimation = cameraScreenState.modelAnimation,
        playPauseAnimation = cameraScreenState::playPauseAnimation,
        callbackAddModelAnimation = cameraScreenState::changeModelAnimation
    )
}

@Composable
fun CameraScreen(
    actionBack: () -> Unit,
    scaffoldState: ScaffoldState,
    playPauseAnimation: () -> Unit,
    modelAnimation: ObjectAnimator?,
    stateModel: Resource<ModelRenderable>,
    callbackAddModelAnimation: (ObjectAnimator) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { ToolbarBack("Camara", actionBack = actionBack) },
        floatingActionButton = {
            Button(onClick = playPauseAnimation) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->

        when (stateModel) {
            Resource.Failure -> Unit
            Resource.Loading -> {
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is Resource.Success -> {
                ArCoreView(
                    model = stateModel.data,
                    isModelAdded = modelAnimation != null,
                    modifier = Modifier.padding(paddingValues),
                    actionTapArPlaneListener = callbackAddModelAnimation
                )
            }
        }
    }
}





