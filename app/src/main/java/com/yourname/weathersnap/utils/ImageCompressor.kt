package com.yourname.weathersnap.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream

object ImageCompressor {

    fun compressImage(
        context: Context,
        imagePath: String
    ): File {

        val originalFile = File(imagePath)

        val bitmap = BitmapFactory.decodeFile(
            originalFile.absolutePath
        )

        val compressedFile = File(
            context.cacheDir,
            "compressed_${originalFile.name}"
        )

        val outputStream =
            FileOutputStream(compressedFile)

        bitmap.compress(
            Bitmap.CompressFormat.JPEG,
            25,
            outputStream
        )

        outputStream.flush()
        outputStream.close()

        return compressedFile
    }
}