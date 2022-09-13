package com.d34th.nullpointer.virtualtrainercompose.domain.exercise

import com.d34th.nullpointer.virtualtrainercompose.models.Exercise

interface ExerciseRepository {
    val listExercise: List<Exercise>
}