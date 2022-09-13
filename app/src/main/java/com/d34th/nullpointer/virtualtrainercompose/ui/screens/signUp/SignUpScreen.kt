package com.d34th.nullpointer.virtualtrainercompose.ui.screens.signUp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableImg
import com.d34th.nullpointer.virtualtrainercompose.ui.screens.signUp.viewModel.SignUpViewModel
import com.d34th.nullpointer.virtualtrainercompose.ui.share.EditableTextSavable
import com.d34th.nullpointer.virtualtrainercompose.ui.share.SelectImgButtonSheet
import com.d34th.nullpointer.virtualtrainercompose.ui.share.SimpleToolbar
import com.d34th.nullpointer.virtualtrainercompose.ui.states.SignUpScreenState
import com.d34th.nullpointer.virtualtrainercompose.ui.states.rememberSignUpScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    signUpScreenState: SignUpScreenState = rememberSignUpScreen()
) {

    ModalBottomSheetLayout(
        sheetState = signUpScreenState.modalBottomSheetState,
        sheetContent = {
            SelectImgButtonSheet(
                isVisible = false,
                actionHidden = signUpScreenState::hiddenModal,
                actionBeforeSelect = { uriImgSelect ->
                    uriImgSelect?.let { signUpViewModel.imgUser.changeValue(it) }
                    signUpScreenState.hiddenModal()
                }
            )
        },
    ) {
        Scaffold(
            topBar = { SimpleToolbar(title = "Registrate") },
            floatingActionButton = {
                Button(onClick = { /*TODO*/ }) {
                    Text("Siguiente")
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
                    propertySavableImg = signUpViewModel.imgUser,
                    actionChangePhoto = signUpScreenState::showModal
                )

                Spacer(modifier = Modifier.height(50.dp))

                EditableTextSavable(
                    valueProperty = signUpViewModel.nameUser,
                    modifier = Modifier.width(300.dp),
                    modifierText = Modifier.fillMaxWidth()
                )
            }
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