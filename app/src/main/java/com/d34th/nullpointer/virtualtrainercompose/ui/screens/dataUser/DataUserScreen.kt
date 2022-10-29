package com.d34th.nullpointer.virtualtrainercompose.ui.screens.dataUser

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.actions.ActionDataScreen
import com.d34th.nullpointer.virtualtrainercompose.actions.ActionDataScreen.*
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableImg
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableString
import com.d34th.nullpointer.virtualtrainercompose.presentation.AuthViewModel
import com.d34th.nullpointer.virtualtrainercompose.presentation.SettingsViewModel
import com.d34th.nullpointer.virtualtrainercompose.ui.share.EditableTextSavable
import com.d34th.nullpointer.virtualtrainercompose.ui.share.ScaffoldModal
import com.d34th.nullpointer.virtualtrainercompose.ui.share.SimpleToolbar
import com.d34th.nullpointer.virtualtrainercompose.ui.share.ToolbarBack
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
    authViewModel: AuthViewModel,
    signUpViewModel: SettingsViewModel,
    signUpScreenState: SignUpScreenState = rememberSignUpScreen()
) {

    LaunchedEffect(key1 = Unit) {
        merge(
            signUpViewModel.messageSignUp,
            authViewModel.messageAuth
        ).collect(signUpScreenState::showSnackMessage)
    }

    DataUserScreen(
        isNewUser = signUpViewModel.isNewUser,
        isModalVisible = signUpScreenState.isShowModal,
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
                    signUpViewModel.updateDataUser()?.let {
                        if (signUpViewModel.isNewUser) {
                            authViewModel.createDataUser(it)
                        } else {
                            authViewModel.updateDataUser(it)
                        }
                    }
                    signUpScreenState.hiddenKeyboard()
                }
                SHOW_MODAL -> signUpScreenState.showModal()
                HIDDEN_MODAL -> signUpScreenState.hiddenModal()
            }
        }
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DataUserScreen(
    isNewUser: Boolean,
    isModalVisible: Boolean,
    scaffoldState: ScaffoldState,
    actionBeforeSelect: (Uri?) -> Unit,
    sheetState: ModalBottomSheetState,
    imagePropertySavableImg: PropertySavableImg,
    namePropertySavable: PropertySavableString,
    actionDataScreen: (ActionDataScreen) -> Unit
) {

    ScaffoldModal(
        sheetState = sheetState,
        scaffoldState = scaffoldState,
        isModalVisible = isModalVisible,
        actionHiddenModal = { actionDataScreen(HIDDEN_MODAL) },
        actionBeforeSelect = actionBeforeSelect,
        topBar = {
            if (isNewUser)
                SimpleToolbar(title = stringResource(R.string.title_sign_up))
            else
                ToolbarBack(
                    title = stringResource(R.string.title_info_user),
                    actionBack = { actionDataScreen(ACTION_BACK) })
        },
        floatingActionButton = {
            Button(onClick = { actionDataScreen(UPDATE_DATA) }) {
                Text(stringResource(R.string.title_accept))
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PhotoProfile(
                modifier = Modifier.padding(20.dp),
                actionChangePhoto = { actionDataScreen(SHOW_MODAL) },
                propertySavableImg = imagePropertySavableImg
            )

            Spacer(modifier = Modifier.height(50.dp))

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
    }
}

@Composable
private fun PhotoProfile(
    modifier: Modifier = Modifier,
    actionChangePhoto: () -> Unit,
    propertySavableImg: PropertySavableImg
) {

    Box(modifier = modifier) {
        Card(shape = CircleShape) {
            Box(contentAlignment = Alignment.Center) {
                SubcomposeAsyncImage(
                    model = propertySavableImg.value,
                    contentDescription = stringResource(id = R.string.description_image_profile),
                    modifier = Modifier.size(180.dp),
                    contentScale = ContentScale.Crop
                ) {

                    when {
                        painter.state is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
                        painter.state is AsyncImagePainter.State.Error && propertySavableImg.isNotEmpty -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_broken_image),
                                contentDescription = stringResource(id = R.string.description_error_load_img),
                                modifier = Modifier.padding(40.dp)
                            )
                        }
                        else -> Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = stringResource(id = R.string.description_image_profile),
                            modifier = Modifier.padding(40.dp)
                        )
                    }
                }

                if (propertySavableImg.isCompress)
                    CircularProgressIndicator(
                        modifier = Modifier.size(30.dp),
                        color = MaterialTheme.colors.primary,
                        strokeWidth = 4.dp
                    )

            }
        }
        FloatingActionButton(
            onClick = actionChangePhoto,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp)
                .size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = stringResource(
                    id = R.string.change_image_user
                )
            )
        }
    }
}