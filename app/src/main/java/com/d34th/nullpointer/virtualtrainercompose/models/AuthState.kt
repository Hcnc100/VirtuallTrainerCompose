package com.d34th.nullpointer.virtualtrainercompose.models

sealed class AuthState {
    data object Authenticating : AuthState()
    class Authenticated(val user: DataUser) : AuthState()
    data object Unauthenticated : AuthState()
}