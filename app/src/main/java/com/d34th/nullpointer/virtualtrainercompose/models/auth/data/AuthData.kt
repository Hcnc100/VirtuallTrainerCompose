package com.d34th.nullpointer.virtualtrainercompose.models.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class AuthData(
    val name: String,
    val pathFile: String
)