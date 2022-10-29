package com.d34th.nullpointer.virtualtrainercompose.models

sealed class AuthState {
    object Authenticating : AuthState()
    class Authenticated(val user: DataUser) : AuthState()
    object Unauthenticated : AuthState()
}