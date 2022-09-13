package com.d34th.nullpointer.virtualtrainercompose.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d34th.nullpointer.virtualtrainercompose.domain.UserSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val settingsRepository: UserSettingsRepository
) : ViewModel() {

    val nameUser = settingsRepository.nameUser.flowOn(Dispatchers.IO).catch {
        Timber.e("Error get name $it")
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ""
    )

    val imgUser = settingsRepository.imgUser.flowOn(Dispatchers.IO).catch {
        Timber.e("Error get img $it")
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ""
    )

    val isSignInUser = combine(nameUser, imgUser) { name, img ->
        name.isNotEmpty() && img.isNotEmpty()
    }

}