package com.d34th.nullpointer.virtualtrainercompose.ui.screens.dataUser.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.d34th.nullpointer.virtualtrainercompose.R

@Composable
fun ButtonEditPhoto(
    actionChangePhoto: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = actionChangePhoto,
        modifier = modifier
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