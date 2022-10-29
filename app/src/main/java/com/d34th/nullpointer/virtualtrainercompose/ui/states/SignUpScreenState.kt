package com.d34th.nullpointer.virtualtrainercompose.ui.states

import android.content.Context
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
class SignUpScreenState(
    context: Context,
    scaffoldState: ScaffoldState,
    private val focusManager: FocusManager,
    private val coroutineScope: CoroutineScope,
    val modalBottomSheetState: ModalBottomSheetState
) : SimpleScreenState(context, scaffoldState) {

    val isShowModal get() = modalBottomSheetState.isVisible

    fun showModal() {
        coroutineScope.launch {
            modalBottomSheetState.show()
        }
    }

    fun hiddenModal() {
        coroutineScope.launch {
            modalBottomSheetState.hide()
        }
    }

    fun hiddenKeyboard() {
        focusManager.clearFocus()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberSignUpScreen(
    context: Context = LocalContext.current,
    focusManager: FocusManager = LocalFocusManager.current,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
) = remember(scaffoldState, coroutineScope, modalBottomSheetState) {
    SignUpScreenState(
        context = context,
        focusManager = focusManager,
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        modalBottomSheetState = modalBottomSheetState
    )
}