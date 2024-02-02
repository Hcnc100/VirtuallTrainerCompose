package com.d34th.nullpointer.virtualtrainercompose.ui.screens.camera.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.core.states.Resource
import com.d34th.nullpointer.virtualtrainercompose.core.utils.launchSafeIO
import com.d34th.nullpointer.virtualtrainercompose.domain.exercise.ExerciseRepository
import com.google.ar.sceneform.rendering.ModelRenderable
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import timber.log.Timber



class CameraViewModel @AssistedInject constructor(
    private val exerciseRepository: ExerciseRepository,
    @Assisted private val nameModel: String
): ViewModel(){

    private val _messageModels = Channel<Int>()
    val messageModels = _messageModels.receiveAsFlow()





    @AssistedFactory
    interface Factory {
        fun create(nameModel: String): CameraViewModel
    }

    companion object {

        fun provideMainViewModelFactory(
            factory: Factory,
            nameModel: String
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return try {
                        factory.create(nameModel) as T
                    } catch (e: Exception) {
                        throw RuntimeException("Cannot create an instance of $modelClass", e)
                    }
                }
            }
        }
    }

}