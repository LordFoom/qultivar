package com.therudeway.qultivar.media

import jakarta.inject.Singleton
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import org.apache.tika.Tika

@Singleton
class MediaUtils {
    fun detectMimeType(fileContent: ByteArray): String {
        val tika = Tika()
        return tika.detect(ByteArrayInputStream(fileContent))
    }

    fun isImageOrVideoMimeType(mimeType: String): Boolean {
        return isImageMimeType(mimeType) || isVideoMimeType(mimeType)
    }

    fun isImageMimeType(mimeType: String): Boolean {
        return mimeType.startsWith("image/")
    }

    fun isVideoMimeType(mimeType: String): Boolean {
        return mimeType.startsWith("video/")
    }

    fun toByteArray(inputStream: InputStream): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val buffer = ByteArray(4096)
        var bytesRead: Int
    
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }
        return outputStream.toByteArray()
    }
}
