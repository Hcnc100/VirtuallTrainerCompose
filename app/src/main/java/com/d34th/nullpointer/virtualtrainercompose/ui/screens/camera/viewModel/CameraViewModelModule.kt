package com.d34th.nullpointer.virtualtrainercompose.ui.screens.camera.viewModel

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@EntryPoint
@InstallIn(ActivityComponent::class)
interface ViewModelFactoryProvider {

    fun cameraViewModelFactory(): CameraViewModel.Factory

}