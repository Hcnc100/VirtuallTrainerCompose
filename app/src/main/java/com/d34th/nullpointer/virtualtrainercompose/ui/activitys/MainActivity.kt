package com.d34th.nullpointer.virtualtrainercompose.ui.activitys

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.d34th.nullpointer.virtualtrainercompose.models.AuthState
import com.d34th.nullpointer.virtualtrainercompose.presentation.AuthViewModel
import com.d34th.nullpointer.virtualtrainercompose.presentation.SettingsViewModel
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.NavGraphs
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.destinations.DataUserScreenDestination
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.destinations.ExercisesScreenDestination
import com.d34th.nullpointer.virtualtrainercompose.ui.theme.VirtualTrainerComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private val signUpViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isSplash = true
        installSplashScreen().apply {
            setKeepOnScreenCondition { isSplash }
        }
        setContent {
            VirtualTrainerComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val stateUser by authViewModel.userState.collectAsState()

                    when (stateUser) {
                        is AuthState.Authenticated -> {
                            signUpViewModel.setInitValues((stateUser as AuthState.Authenticated).user)
                            ExercisesScreenDestination
                        }
                        AuthState.Unauthenticated -> DataUserScreenDestination
                        AuthState.Authenticating -> null
                    }?.let {
                        isSplash = false
                        DestinationsNavHost(
                            navController = rememberNavController(),
                            navGraph = NavGraphs.root,
                            startRoute = it,
                            dependenciesContainerBuilder = {
                                dependency(signUpViewModel)
                                dependency(authViewModel)
                            }
                        )
                    }

                }
            }
        }
    }
}

