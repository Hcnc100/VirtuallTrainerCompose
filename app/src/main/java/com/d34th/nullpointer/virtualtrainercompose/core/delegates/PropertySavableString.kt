package com.d34th.nullpointer.virtualtrainercompose.core.delegates

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import com.d34th.nullpointer.virtualtrainercompose.R

class PropertySavableString(
    tagSavable: String,
    @StringRes val hint: Int,
    @StringRes val label: Int,
    private val maxLength: Int,
    savedState: SavedStateHandle,
    private var defaultValue: String = "",
    @StringRes private val emptyError: Int = UNDEFINE_RESOURCE,
    @StringRes private val lengthError: Int = UNDEFINE_RESOURCE,
) {


    companion object {
        private const val UNDEFINE_RESOURCE = -1


        val example = PropertySavableString(
            tagSavable = "EXAMPLE",
            hint = R.string.hint_example,
            label = R.string.label_example,
            maxLength = 50,
            savedState = SavedStateHandle(),
            defaultValue = "DEFAULT",
            emptyError = R.string.error_empty_example,
            lengthError = R.string.error_length_example
        )
    }

    private val idSaved = "SAVED_PROPERTY_$tagSavable"

    var currentValue by SavableComposeState(savedState, "$idSaved-CURRENT-VALUE", defaultValue)
        private set

    var errorValue by SavableComposeState(savedState, "$idSaved-ERROR-VALUE", UNDEFINE_RESOURCE)
        private set

    val hasChanged: Boolean get() = this.currentValue != defaultValue

    val countLength get() = "${currentValue.length}/$maxLength"

    val hasError get() = errorValue != UNDEFINE_RESOURCE

    val isEmpty get() = currentValue.isEmpty()


    fun setDefaultValue(newValue: String) {
        defaultValue = newValue
        currentValue = newValue
    }

    fun changeValue(newValue: String) {
        currentValue = newValue
        errorValue = when {
            newValue.isEmpty() && emptyError != UNDEFINE_RESOURCE -> emptyError
            newValue.length > maxLength && lengthError != UNDEFINE_RESOURCE -> lengthError
            else -> UNDEFINE_RESOURCE
        }

    }

    fun getValueOnlyHasChanged(): String? {
        return when {
            hasChanged -> currentValue
            else -> null
        }
    }

    fun setAnotherError(@StringRes newError: Int) {
        errorValue = newError
    }

    fun clearValue() {
        currentValue = ""
        errorValue = UNDEFINE_RESOURCE
    }

    fun reValueField() = changeValue(currentValue)
}