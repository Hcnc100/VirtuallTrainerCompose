package com.d34th.nullpointer.virtualtrainercompose.ui.states

import android.animation.ObjectAnimator
import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

class CameraScreenState(
    context: Context,
    scaffoldState: ScaffoldState
) : SimpleScreenState(context, scaffoldState) {

    var modelAnimation by mutableStateOf<ObjectAnimator?>(null)
        private set

    fun changeModelAnimation(modelAnimation: ObjectAnimator) {
        this.modelAnimation = modelAnimation
    }


    fun playPauseAnimation() {
        modelAnimation?.let {
            when {
                it.isPaused -> it.resume()
                it.isStarted -> it.pause()
                else -> it.start()
            }
        }
    }
}


@Composable
fun rememberCameraScreenState(
    context: Context = LocalContext.current,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) = remember {
    CameraScreenState(context, scaffoldState)
}