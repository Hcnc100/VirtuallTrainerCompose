package com.d34th.nullpointer.virtualtrainercompose.ui.screens.dataUser

import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.actions.ActionDataScreen
import com.d34th.nullpointer.virtualtrainercompose.actions.ActionDataScreen.*
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableImg
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableString
import com.d34th.nullpointer.virtualtrainercompose.presentation.AuthViewModel
import com.d34th.nullpointer.virtualtrainercompose.presentation.SettingsViewModel
import com.d34th.nullpointer.virtualtrainercompose.ui.preview.BooleanProvider
import com.d34th.nullpointer.virtualtrainercompose.ui.preview.config.OrientationPreviews
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.dataUser.components.PhotoProfile
import com.d34th.nullpointer.virtualtrainercompose.ui.share.EditableTextSavable
import com.d34th.nullpointer.virtualtrainercompose.ui.share.SimpleToolbar
import com.d34th.nullpointer.virtualtrainercompose.ui.share.ToolbarBack
import com.d34th.nullpointer.virtualtrainercompose.ui.share.bottomSheetSelectImage.BottomSheetSelectImage
import com.d34th.nullpointer.virtualtrainercompose.ui.states.SignUpScreenState
import com.d34th.nullpointer.virtualtrainercompose.ui.states.rememberSignUpScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.merge


@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun DataUserScreen(
    navigator: DestinationsNavigator,
    signUpViewModel: SettingsViewModel = hiltViewModel(),
    signUpScreenState: SignUpScreenState = rememberSignUpScreen()
) {

    LaunchedEffect(key1 = Unit) {
        signUpViewModel.messageSignUp.collect(signUpScreenState::showSnackMessage)
    }

    DataUserScreen(
        isNewUser = signUpViewModel.isNewUser,
        namePropertySavable = signUpViewModel.nameUser,
        scaffoldState = signUpScreenState.scaffoldState,
        imagePropertySavableImg = signUpViewModel.imgUser,
        sheetState = signUpScreenState.modalBottomSheetState,
        actionBeforeSelect = { uriImgSelect ->
            uriImgSelect?.let { signUpViewModel.imgUser.changeValue(it) }
            signUpScreenState.hiddenModal()
        },
        actionDataScreen = { action ->
            when (action) {
                ACTION_BACK -> navigator.popBackStack()
                UPDATE_DATA -> {
                    signUpScreenState.hiddenKeyboard()
                    signUpViewModel.getAuthWrapper()?.let {
                        signUpViewModel.saveDataUser(it)
                    }
                }

                SHOW_MODAL -> signUpScreenState.showModal()
                HIDDEN_MODAL -> signUpScreenState.hiddenModal()
            }
        }
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DataUserScreen(
    isNewUser: Boolean,
    scaffoldState: ScaffoldState,
    actionBeforeSelect: (Uri?) -> Unit,
    sheetState: ModalBottomSheetState,
    imagePropertySavableImg: PropertySavableImg,
    namePropertySavable: PropertySavableString,
    actionDataScreen: (ActionDataScreen) -> Unit,
    config: Configuration = LocalConfiguration.current
) {

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheetSelectImage(
                isVisible = sheetState.isVisible,
                actionBeforeSelect = actionBeforeSelect,
                actionHidden = { actionDataScreen(HIDDEN_MODAL) }
            )
        }) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                when (isNewUser) {
                    true -> SimpleToolbar(title = stringResource(R.string.title_sign_up))
                    false -> ToolbarBack(
                        title = stringResource(R.string.title_info_user),
                        actionBack = { actionDataScreen(ACTION_BACK) }
                    )
                }
            }) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {
                when (config.orientation) {
                    ORIENTATION_PORTRAIT -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((config.screenHeightDp.dp - 64.dp)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                PhotoProfile(
                                    modifier = Modifier.padding(20.dp),
                                    photo = imagePropertySavableImg.value,
                                    editEnabled = !imagePropertySavableImg.isLoading,
                                    actionChangePhoto = { actionDataScreen(SHOW_MODAL) }
                                )

                                EditableTextSavable(
                                    singleLine = true,
                                    modifier = Modifier.width(300.dp),
                                    valueProperty = namePropertySavable,
                                    modifierText = Modifier.fillMaxWidth(),
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            actionDataScreen(UPDATE_DATA)
                                        }
                                    )
                                )
                            }
                            Button(
                                onClick = { actionDataScreen(UPDATE_DATA) }
                            ) {
                                Text(stringResource(R.string.title_accept))
                            }
                        }
                    }

                    else -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height((config.screenHeightDp.dp - 64.dp)),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PhotoProfile(
                                modifier = Modifier.padding(20.dp),
                                photo = imagePropertySavableImg.value,
                                editEnabled = !imagePropertySavableImg.isLoading,
                                actionChangePhoto = { actionDataScreen(SHOW_MODAL) }
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                EditableTextSavable(
                                    singleLine = true,
                                    modifier = Modifier.width(300.dp),
                                    valueProperty = namePropertySavable,
                                    modifierText = Modifier.fillMaxWidth(),
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            actionDataScreen(UPDATE_DATA)
                                        }
                                    )
                                )
                                Button(
                                    onClick = { actionDataScreen(UPDATE_DATA) }
                                ) {
                                    Text(stringResource(R.string.title_accept))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OrientationPreviews
@Composable
private fun DataUserScreenPreview(
    @PreviewParameter(BooleanProvider::class)
    isNewUser: Boolean
) {
    DataUserScreen(
        actionDataScreen = {},
        isNewUser = isNewUser,
        actionBeforeSelect = {},
        scaffoldState = rememberScaffoldState(),
        namePropertySavable = PropertySavableString.example,
        imagePropertySavableImg = PropertySavableImg.example,
        sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    )
}

