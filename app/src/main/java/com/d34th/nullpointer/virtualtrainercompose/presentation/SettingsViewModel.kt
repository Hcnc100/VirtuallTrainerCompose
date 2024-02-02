package com.d34th.nullpointer.virtualtrainercompose.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableImg
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableString
import com.d34th.nullpointer.virtualtrainercompose.core.utils.launchSafeIO
import com.d34th.nullpointer.virtualtrainercompose.domain.compress.CompressRepository
import com.d34th.nullpointer.virtualtrainercompose.domain.settings.AuthRepository
import com.d34th.nullpointer.virtualtrainercompose.models.auth.wrapper.AuthWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    savedInstanceState: SavedStateHandle,
    private val authRepository: AuthRepository,
    private val compressRepository: CompressRepository,
) : ViewModel() {

    companion object {
        private const val MAX_LENGTH_NAME = 50
        private const val TAG_NAME_USER = "TAG_NAME_USER"
        private const val TAG_IMG_USER = "TAG_IMG_USER"
    }

    private val _messageSignUp = Channel<Int>()
    val messageSignUp = _messageSignUp.receiveAsFlow()


    val nameUser = PropertySavableString(
        savedState = savedInstanceState,
        tagSavable = TAG_NAME_USER,
        maxLength = MAX_LENGTH_NAME,
        hint = R.string.hint_name_user,
        label = R.string.label_name_user,
        emptyError = R.string.error_empty_name,
        lengthError = R.string.error_length_name,
    )

    val imgUser = PropertySavableImg(
        tagSavable = TAG_IMG_USER,
        state = savedInstanceState
    )

    var isNewUser by mutableStateOf(true)
        private set


    init {
        loadUserData()
    }

    private fun loadUserData() = viewModelScope.launch(
        Dispatchers.IO
    ) {
     authRepository.currentUser.first()?.let {
        nameUser.setDefaultValue(it.name)
        imgUser.setDefaultValue(it.pathFile.toUri())
         withContext(Dispatchers.Main){
             isNewUser = false
         }
     }
    }

    fun saveDataUser(
        authWrapper: AuthWrapper
    ) = launchSafeIO {
        authRepository.saveAuthData(authWrapper)
    }

    fun getAuthWrapper(): AuthWrapper? {
        return when {
            nameUser.hasError->{
                _messageSignUp.trySend(R.string.message_name_error)
                null
            }
           imgUser.isEmpty -> {
                _messageSignUp.trySend(R.string.message_img_error)
                null
            }
            else -> {
                AuthWrapper(
                    image = imgUser.getValueOnlyHasChanged(),
                    name = nameUser.getValueOnlyHasChanged(),
                )
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        imgUser.clearValue()
        nameUser.clearValue()
    }


}