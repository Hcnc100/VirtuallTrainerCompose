package com.d34th.nullpointer.virtualtrainercompose.domain.compress

import android.net.Uri

interface CompressRepository {
    suspend fun compressImage(uri: Uri): Uri
}