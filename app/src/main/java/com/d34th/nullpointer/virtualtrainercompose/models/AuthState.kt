package com.d34th.nullpointer.virtualtrainercompose.models

import com.d34th.nullpointer.virtualtrainercompose.models.auth.data.AuthData

sealed class AuthState {
    data object Authenticating : AuthState()
    class Authenticated(val user: AuthData) : AuthState()
    data object Unauthenticated : AuthState()
}