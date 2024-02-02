package com.d34th.nullpointer.virtualtrainercompose.ui.share.bottomSheetSelectImage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.ui.preview.config.SimplePreview

@Composable
fun ItemButtonSheet(
    iconResource: Painter,
    textOptionRes: String,
    textDescriptionRes: String,
    actionLaunch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { actionLaunch() }
        .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Icon(
            painter = iconResource,
            tint = MaterialTheme.colors.onSurface,
            contentDescription = textDescriptionRes
        )
        Text(text = textOptionRes)
    }
}

@SimplePreview
@Composable
fun ItemBottomSheetPreview() {
    ItemButtonSheet(
        iconResource = painterResource(id = R.drawable.ic_person),
        textOptionRes = stringResource(id = R.string.text_select_option),
        textDescriptionRes = stringResource(id = R.string.text_select_option),
        actionLaunch = {}
    )
}