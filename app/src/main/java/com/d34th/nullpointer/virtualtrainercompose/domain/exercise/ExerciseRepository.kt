package com.d34th.nullpointer.virtualtrainercompose.domain.exercise

import com.d34th.nullpointer.virtualtrainercompose.models.Exercise
import com.google.ar.sceneform.rendering.ModelRenderable

interface ExerciseRepository {
    val listExercise: List<Exercise>

    suspend fun getModelForExercise(nameModel: String): ModelRenderable
}