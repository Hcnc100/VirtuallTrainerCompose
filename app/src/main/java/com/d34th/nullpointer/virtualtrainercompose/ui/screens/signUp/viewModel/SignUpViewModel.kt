package com.d34th.nullpointer.virtualtrainercompose.ui.screens.signUp.viewModel

import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableImg
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableString
import com.d34th.nullpointer.virtualtrainercompose.domain.settings.UserSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    savedInstanceState: SavedStateHandle,
    private val userSettingsRepository: UserSettingsRepository
) : ViewModel() {

    companion object {
        private const val MAX_LENGTH_NAME = 50
        private const val TAG_NAME_USER = "TAG_NAME_USER"
        private const val TAG_IMG_USER = "TAG_IMG_USER"
    }

    private val _messageSignUp = Channel<Int>()
    val messageSignUp = _messageSignUp.receiveAsFlow()

    val nameUser = PropertySavableString(
        state = savedInstanceState,
        tagSavable = TAG_NAME_USER,
        maxLength = MAX_LENGTH_NAME,
        hint = R.string.hint_name_user,
        label = R.string.label_name_user,
        emptyError = R.string.error_empty_name,
        lengthError = R.string.error_length_name
    )

    val imgUser = PropertySavableImg(
        tagSavable = TAG_IMG_USER,
        state = savedInstanceState,
        scope = viewModelScope,
        actionCompress = { userSettingsRepository.compressImgUri(it).toUri() },
        actionSendErrorCompress = { _messageSignUp.trySend(R.string.error_compress_img) }
    )

    override fun onCleared() {
        super.onCleared()
        imgUser.clearValue()
        nameUser.clearValue()
    }


}