package com.d34th.nullpointer.virtualtrainercompose.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.d34th.nullpointer.virtualtrainercompose.R
import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val nameModel: String
){
    companion object{
        val example = Exercise(
            image = R.drawable.ejercicio,
            title = R.string.label_example,
            description = R.string.error_length_example,
            nameModel = "example"
        )
    }
}