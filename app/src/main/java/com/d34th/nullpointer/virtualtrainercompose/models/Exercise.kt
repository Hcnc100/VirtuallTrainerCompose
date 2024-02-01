package com.d34th.nullpointer.virtualtrainercompose.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val nameModel: String
)