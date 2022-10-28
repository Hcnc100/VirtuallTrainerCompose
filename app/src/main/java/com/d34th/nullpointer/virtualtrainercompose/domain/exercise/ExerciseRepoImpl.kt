package com.d34th.nullpointer.virtualtrainercompose.domain.exercise

import android.content.Context
import android.net.Uri
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.models.Exercise
import com.google.ar.sceneform.rendering.ModelRenderable
import com.gorisse.thomas.sceneform.scene.await

class ExerciseRepoImpl(
    private val context: Context
) : ExerciseRepository {

    override val listExercise = listOf(
        Exercise(
            R.drawable.squats,
            R.string.squats,
            R.string.squats_descripcion,
            "squats.glb"
        ),
        Exercise(
            R.drawable.abdominales,
            R.string.abdominales,
            R.string.abdominales_descripcion,
            "abdominales.glb"
        ),
        Exercise(
            R.drawable.ejercicio,
            R.string.saltos_laterales,
            R.string.saltos_laterales_descripcion,
            "jumping_jacks.glb"
        ),
        Exercise(
            R.drawable.push,
            R.string.lagartijas,
            R.string.lagartijas_descripcion,
            "push_up.glb"
        ),
        Exercise(
            R.drawable.tablon,
            R.string.plancha,
            R.string.plancha_descripcion,
            "plank.glb"
        ),
        Exercise(
            R.drawable.abdominales,
            R.string.abdominales_cycle,
            R.string.abdominales_cycle_descripcion,
            "bicycle_crunch.glb"
        )
    )

    override suspend fun getModelForExercise(nameModel: String): ModelRenderable {
        return ModelRenderable.builder()
            .setSource(context, Uri.parse(nameModel))
            .setIsFilamentGltf(true)
            .await()
    }

}