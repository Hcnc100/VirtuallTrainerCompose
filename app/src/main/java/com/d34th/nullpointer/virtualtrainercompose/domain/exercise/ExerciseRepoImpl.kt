package com.d34th.nullpointer.virtualtrainercompose.domain.exercise

import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.models.Exercise

class ExerciseRepoImpl : ExerciseRepository {

    override val listExercise = listOf(
        Exercise(
            R.drawable.squats,
            R.string.squats,
            R.string.squats_descripcion,
            "squat.sfb"
        ),
        Exercise(
            R.drawable.abdominales,
            R.string.abdominales,
            R.string.abdominales_descripcion,
            "abdominales.sfb"
        ),
        Exercise(
            R.drawable.ejercicio,
            R.string.saltos_laterales,
            R.string.saltos_laterales_descripcion,
            "JumpingJacks.sfb"
        ),
        Exercise(
            R.drawable.push,
            R.string.lagartijas,
            R.string.lagartijas_descripcion,
            "PushUp.sfb"
        ),
        Exercise(
            R.drawable.tablon,
            R.string.plancha,
            R.string.plancha_descripcion,
            "Plank.sfb"
        ),
        Exercise(
            R.drawable.abdominales,
            R.string.abdominales_cycle,
            R.string.abdominales_cycle_descripcion,
            "BicycleCrunch.sfb"
        )
    )

}