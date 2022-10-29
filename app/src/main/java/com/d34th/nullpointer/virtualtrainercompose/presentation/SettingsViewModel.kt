package com.d34th.nullpointer.virtualtrainercompose.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableImg
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableString
import com.d34th.nullpointer.virtualtrainercompose.domain.compress.CompressRepository
import com.d34th.nullpointer.virtualtrainercompose.models.DataUser
import com.d34th.nullpointer.virtualtrainercompose.models.FieldsUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    savedInstanceState: SavedStateHandle,
    private val compressRepository: CompressRepository
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
        actionCompress = { compressRepository.compressImage(it) },
        actionSendErrorCompress = { _messageSignUp.trySend(R.string.error_compress_img) }
    )

    var isNewUser by mutableStateOf(true)
        private set


    fun setInitValues(currentUser: DataUser) {
        if (currentUser.name.isNotEmpty() && currentUser.pathFile.isNotEmpty()) {
            nameUser.initValue(currentUser.name)
            imgUser.initValue(currentUser.pathFile)
            isNewUser = false
        }
    }

    fun updateDataUser(): FieldsUser? {
        return when {
            nameUser.hasError || !imgUser.isNotEmpty -> {
                _messageSignUp.trySend(R.string.message_need_data)
                null
            }
            else -> {
                FieldsUser(
                    name = nameUser.currentValue,
                    imgUrl = imgUser.value
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