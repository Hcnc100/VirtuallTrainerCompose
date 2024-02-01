package com.d34th.nullpointer.virtualtrainercompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.core.utils.launchSafeIO
import com.d34th.nullpointer.virtualtrainercompose.domain.settings.AuthRepository
import com.d34th.nullpointer.virtualtrainercompose.models.AuthState
import com.d34th.nullpointer.virtualtrainercompose.models.FieldsUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val settingsRepository: AuthRepository
) : ViewModel() {

    private val _messageAuth = Channel<Int>()
    val messageAuth = _messageAuth.receiveAsFlow()

    val userState = settingsRepository.currentUser.map {
        when(it){
            null -> AuthState.Unauthenticated
            else -> AuthState.Authenticated(it)
        }
    }.catch {
        emit(AuthState.Unauthenticated)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        AuthState.Authenticating
    )

    fun updateDataUser(fields: FieldsUser) = launchSafeIO {
        val currentUser = settingsRepository.currentUser.first()
        // ! TODO fix this
//        if (currentUser.name == fields.name && currentUser.pathFile == fields.imgUrl?.path) {
//            _messageAuth.trySend(R.string.message_no_changes)
//        } else {
//            settingsRepository.changeDataUser(
//                nameUser = if (currentUser.name != fields.name) fields.name else null,
//                uriImg = if (currentUser.pathFile != fields.imgUrl?.path) fields.imgUrl else null
//            )
//            _messageAuth.trySend(R.string.message_data_updated)
//        }

    }

    fun createDataUser(fields: FieldsUser) = launchSafeIO {
        settingsRepository.saveUser(
            name = fields.name,
            uriImg = fields.imgUrl!!
        )
    }

}