package com.d34th.nullpointer.virtualtrainercompose.ui.share.bottomSheetSelectImage

import android.content.Context
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.d34th.nullpointer.virtualtrainercompose.BuildConfig
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.ui.preview.config.SimplePreview
import java.io.File


@Composable
fun BottomSheetSelectImage(
    isVisible: Boolean,
    actionHidden: () -> Unit,
    actionBeforeSelect: (Uri?) -> Unit,
    context: Context = LocalContext.current
) {
    BackHandler(enabled = isVisible, onBack = actionHidden)

    val tmpUri = rememberSaveable { mutableStateOf<Uri?>(null) }

    val launcherPhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { isCapture ->
        val response = if (isCapture) tmpUri.value else null
        actionBeforeSelect(response)
    }

    val launcherImg = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        actionBeforeSelect(uri)
    }

    BottomSheetSelectImage(
        actionSelectImg = {
            tmpUri.value = getTmpFileUri(context)
            launcherImg.launch("image/*")
        },
        actionTakePhoto = {
            tmpUri.value = getTmpFileUri(context)
            launcherPhoto.launch(tmpUri.value)
        }
    )
}

@Composable
fun BottomSheetSelectImage(
    actionSelectImg: () -> Unit,
    actionTakePhoto: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            text = stringResource(R.string.text_select_option),
            modifier = Modifier.padding(10.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        ItemButtonSheet(
            actionLaunch = actionTakePhoto,

            iconResource = painterResource(id = R.drawable.ic_camera),
            textOptionRes = stringResource(id = R.string.option_img_camera),
            textDescriptionRes = stringResource(id = R.string.description_img_camera),
        )
        ItemButtonSheet(
            actionLaunch = actionSelectImg,
            iconResource = painterResource(id = R.drawable.ic_image),
            textOptionRes = stringResource(id = R.string.option_img_gallery),
            textDescriptionRes = stringResource(id = R.string.description_img_gallery)
        )

    }
}

@SimplePreview
@Composable
private fun BottomSheetSelectImagePreview() {
    BottomSheetSelectImage(
        actionSelectImg = {},
        actionTakePhoto = {}
    )
}


// * get cache file from system provider
private fun getTmpFileUri(context: Context): Uri {
    val tmpFile = File.createTempFile("tmp_image_file", ".png").apply {
        createNewFile()
        deleteOnExit()
    }
    return FileProvider.getUriForFile(
        context,
        "${BuildConfig.APPLICATION_ID}.provider",
        tmpFile
    )
}






