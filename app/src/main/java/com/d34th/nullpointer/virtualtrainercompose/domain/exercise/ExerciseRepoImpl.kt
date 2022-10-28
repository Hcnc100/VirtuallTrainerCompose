package com.d34th.nullpointer.virtualtrainercompose.domain.exercise

import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.models.Exercise

class ExerciseRepoImpl : ExerciseRepository {

    override val listExercise = listOf(
        Exercise(
            R.drawable.squats,
            R.string.squats,
            R.string.squats_descripcion,
            R.raw.squats
        ),
        Exercise(
            R.drawable.abdominales,
            R.string.abdominales,
            R.string.abdominales_descripcion,
            R.raw.abdominales
        ),
        Exercise(
            R.drawable.ejercicio,
            R.string.saltos_laterales,
            R.string.saltos_laterales_descripcion,
            R.raw.jumping_jacks
        ),
        Exercise(
            R.drawable.push,
            R.string.lagartijas,
            R.string.lagartijas_descripcion,
            R.raw.push_up
        ),
        Exercise(
            R.drawable.tablon,
            R.string.plancha,
            R.string.plancha_descripcion,
            R.raw.plank
        ),
        Exercise(
            R.drawable.abdominales,
            R.string.abdominales_cycle,
            R.string.abdominales_cycle_descripcion,
            R.raw.bicycle_crunch
        )
    )

}