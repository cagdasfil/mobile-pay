package com.example.mobilepay.ui.screens.pos.model

import kotlinx.serialization.Serializable

@Serializable
data class QRData(val returnCode: Int, val returnDesc: String, val QRdata: String)
