package com.example.mobilepay.ui.utils

import android.graphics.Bitmap
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder

object QRGenerator {
    fun getQRBitmapByString(qrString: String): Bitmap {
        val qrgEncoder = QRGEncoder(qrString, null, QRGContents.Type.TEXT, 500)
        return qrgEncoder.bitmap
    }
}