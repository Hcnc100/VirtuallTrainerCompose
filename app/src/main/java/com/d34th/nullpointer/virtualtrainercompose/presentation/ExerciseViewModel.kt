package com.d34th.nullpointer.virtualtrainercompose.presentation

import androidx.lifecycle.ViewModel
import com.d34th.nullpointer.virtualtrainercompose.domain.exercise.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ExerciseViewModel constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    val listExercise = exerciseRepository.listExercise

}