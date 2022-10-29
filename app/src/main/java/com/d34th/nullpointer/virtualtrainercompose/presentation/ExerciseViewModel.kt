package com.d34th.nullpointer.virtualtrainercompose.presentation

import androidx.lifecycle.ViewModel
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.core.states.Resource
import com.d34th.nullpointer.virtualtrainercompose.core.utils.launchSafeIO
import com.d34th.nullpointer.virtualtrainercompose.domain.exercise.ExerciseRepository
import com.google.ar.sceneform.rendering.ModelRenderable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {


    private val _messageModels = Channel<Int>()
    val messageModels = _messageModels.receiveAsFlow()

    val listExercise = exerciseRepository.listExercise

    private val _modelRenderable = MutableStateFlow<Resource<ModelRenderable>>(Resource.Loading)
    val modelRenderable = _modelRenderable.asStateFlow()


    fun loadModel(nameModel: String) = launchSafeIO(
        blockBefore = { _modelRenderable.value = Resource.Loading },
        blockException = {
            _modelRenderable.value = Resource.Failure
            Timber.d("Error load model $it")
            _messageModels.trySend(R.string.error_load_model)
        },
        blockIO = {
            _modelRenderable.value = withContext(Dispatchers.Main) {
                Resource.Success(exerciseRepository.getModelForExercise(nameModel))
            }
        }
    )

}