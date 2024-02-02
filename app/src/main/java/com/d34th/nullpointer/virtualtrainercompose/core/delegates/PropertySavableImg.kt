package com.d34th.nullpointer.virtualtrainercompose.core.delegates

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle

class PropertySavableImg(
    tagSavable: String,
    state: SavedStateHandle,
    private var defaultValue: Uri = Uri.EMPTY,
) {

    companion object {
        val example = PropertySavableImg(
            tagSavable = "example",
            state = SavedStateHandle()
        )
    }

    private val idSaved = "SAVED_PROPERTY_IMG_$tagSavable"

    var value: Uri by SavableComposeState(state, "$idSaved-CURRENT-VALUE", Uri.EMPTY)
        private set

    var isLoading by mutableStateOf(false)
        private set

    val hasChanged get() = value != defaultValue

    val isNotEmpty get() = value != Uri.EMPTY

    val isEmpty get() = value == Uri.EMPTY

    fun getValueOnlyHasChanged(): Uri? {
        return when {
            hasChanged -> value
            else -> null
        }
    }

    fun setDefaultValue(newValue: Uri) {
        defaultValue = newValue
        value = newValue
    }

    fun changeValue(newValue: Uri) {
        value = newValue
    }

    fun changeImageLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    fun clearValue() {
        isLoading = false
        value = defaultValue
    }
}