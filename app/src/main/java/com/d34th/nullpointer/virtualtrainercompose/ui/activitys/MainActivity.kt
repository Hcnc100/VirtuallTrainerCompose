package com.d34th.nullpointer.virtualtrainercompose.ui.activitys

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.camera.CameraScreen
import com.d34th.nullpointer.virtualtrainercompose.ui.theme.VirtualTrainerComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VirtualTrainerComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    ExercisesScreen()
                    CameraScreen()
                }
            }
        }
    }
}

