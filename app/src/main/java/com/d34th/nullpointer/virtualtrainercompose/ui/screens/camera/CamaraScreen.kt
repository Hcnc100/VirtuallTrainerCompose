package com.d34th.nullpointer.virtualtrainercompose.ui.screens.camera

import android.animation.ObjectAnimator
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.d34th.nullpointer.virtualtrainercompose.core.states.Resource
import com.d34th.nullpointer.virtualtrainercompose.core.utils.shareViewModel
import com.d34th.nullpointer.virtualtrainercompose.presentation.ExerciseViewModel
import com.d34th.nullpointer.virtualtrainercompose.ui.share.ArCoreView
import com.d34th.nullpointer.virtualtrainercompose.ui.share.SimpleToolbar
import com.google.ar.sceneform.rendering.ModelRenderable
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CameraScreen(
    exerciseViewModel: ExerciseViewModel = shareViewModel()
) {

    val stateModel by exerciseViewModel.modelRenderable.collectAsState()

    var modelAnimation by remember {
        mutableStateOf<ObjectAnimator?>(null)
    }


    CameraScreen(
        stateModel = stateModel,
        modelAnimation = modelAnimation,
        callbackAddModelAnimation = { modelAnimation = it })
}

@Composable
fun CameraScreen(
    modelAnimation: ObjectAnimator?,
    stateModel: Resource<ModelRenderable>,
    callbackAddModelAnimation: (ObjectAnimator) -> Unit
) {
    Scaffold(
        topBar = { SimpleToolbar("Camara") },
        floatingActionButton = {
            Button(onClick = {
                controlAnimationModel(modelAnimation)
            }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->

        when (stateModel) {
            Resource.Failure -> {

            }
            Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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

fun controlAnimationModel(modelAnimation: ObjectAnimator?) {
    modelAnimation?.let {
        when {
            it.isPaused -> it.resume()
            it.isStarted -> it.pause()
            else -> it.start()
        }
    }
}



