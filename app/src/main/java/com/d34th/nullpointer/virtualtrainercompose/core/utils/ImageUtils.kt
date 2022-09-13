package com.d34th.nullpointer.virtualtrainercompose.core.utils

import android.content.Context
import timber.log.Timber
import java.io.File

object ImageUtils {
    fun saveToInternalStorage(
        fileImg: File,
        nameFile: String,
        context: Context,
    ): String? {
        return try {
            context.openFileOutput(nameFile, Context.MODE_PRIVATE).use { output ->
                val bites = fileImg.readBytes()
                output.write(bites)
            }
            context.getFileStreamPath(nameFile).absolutePath
        } catch (e: Exception) {
            Timber.e("Error saved image $nameFile : $e")
            null
        }
    }

    fun deleterImgFromStorage(fullPath: String, context: Context) {
        try {
            val nameFile = fullPath.split("/").last()
            context.deleteFile(nameFile)
        } catch (e: Exception) {
            Timber.e("Error deleter img $fullPath : $e")
        }
    }
}