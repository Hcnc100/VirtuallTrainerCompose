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

    var isPlayingAnimation by mutableStateOf(false)

    var isShowDialog by mutableStateOf(false)


    fun changeVisibilityExplainDialog(isShowDialog: Boolean) {
        this.isShowDialog = isShowDialog
    }

    fun playPauseAnimation() {
        modelAnimation?.let {
            when {
                it.isPaused -> {
                    isPlayingAnimation = true
                    it.resume()
                }
                it.isStarted -> {
                    isPlayingAnimation = false
                    it.pause()
                }
                else -> {
                    isPlayingAnimation = true
                    it.start()
                }
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