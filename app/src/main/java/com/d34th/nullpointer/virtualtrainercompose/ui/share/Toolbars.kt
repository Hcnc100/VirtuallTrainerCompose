package com.d34th.nullpointer.virtualtrainercompose.ui.share

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.d34th.nullpointer.virtualtrainercompose.R

@Composable
fun ToolbarBack(title: String, actionBack: () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = { Text(title) },
        contentColor = Color.White,
        navigationIcon = {
            IconButton(onClick = actionBack) {
                Icon(
                    painterResource(id = R.drawable.ic_arrow),
                    stringResource(id = R.string.description_arrow_back)
                )
            }
        })
}

@Composable
fun SimpleToolbar(title: String) {
    TopAppBar(
        contentColor = Color.White,
        backgroundColor = MaterialTheme.colors.primary,
        title = { Text(title) })
}

@Composable
fun ToolbarSettings(title: String, actionSettings: () -> Unit) {
    TopAppBar(
        contentColor = Color.White,
        backgroundColor = MaterialTheme.colors.primary,
        title = { Text(title) },
        actions = {
            IconButton(onClick = actionSettings) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = null
                )
            }
        }
    )
}