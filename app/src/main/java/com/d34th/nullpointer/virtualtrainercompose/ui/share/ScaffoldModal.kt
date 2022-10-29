package com.d34th.nullpointer.virtualtrainercompose.ui.share

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScaffoldModal(
    isModalVisible: Boolean,
    scaffoldState: ScaffoldState,
    actionHiddenModal: () -> Unit,
    actionBeforeSelect: (Uri?) -> Unit,
    sheetState: ModalBottomSheetState,
    topBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    content: @Composable (PaddingValues) -> Unit,

    ) {
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            SelectImgButtonSheet(
                isVisible = isModalVisible,
                actionHidden = actionHiddenModal,
                actionBeforeSelect = actionBeforeSelect
            )
        },
    ) {
        Scaffold(
            topBar = topBar,
            content = content,
            scaffoldState = scaffoldState,
            floatingActionButton = floatingActionButton,
            floatingActionButtonPosition = floatingActionButtonPosition
        )
    }
}