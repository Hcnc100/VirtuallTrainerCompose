package com.d34th.nullpointer.virtualtrainercompose.ui.screens.dataUser.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.core.delegates.PropertySavableImg
import com.d34th.nullpointer.virtualtrainercompose.ui.preview.BooleanProvider
import com.d34th.nullpointer.virtualtrainercompose.ui.preview.config.SimplePreview

@Composable
fun PhotoProfile(
    photo:Uri,
    editEnabled: Boolean,
    modifier: Modifier = Modifier,
    actionChangePhoto: () -> Unit,
    context: Context = LocalContext.current,
) {

    Box(modifier = modifier) {
        SubcomposeAsyncImage(
            model = ImageRequest
                .Builder(context)
                .data(photo)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.description_image_profile),
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            loading = {
                LoadingIconImageProfile(
                    showLoading = true,
                    modifier = Modifier.fillMaxSize()
                )
            },
            error = {
                LoadingIconImageProfile(
                    showLoading = false,
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
        if (editEnabled) {
            ButtonEditPhoto(
                actionChangePhoto = actionChangePhoto,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}


@SimplePreview
@Composable
private fun PhotoProfilePreview(
    @PreviewParameter(BooleanProvider::class)
    editEnabled: Boolean,
) {
    PhotoProfile(
        photo = Uri.EMPTY,
        editEnabled = editEnabled,
        actionChangePhoto = { /*TODO*/ },
    )
}

