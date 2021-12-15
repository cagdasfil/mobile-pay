package com.example.mobilepay.ui.screens.app.model

import kotlinx.serialization.Serializable

@Serializable
data class PaymentResult(
    val applicationID: String,
    val sessionID: String,
    val posID: String,
    val returnCode: Int,
    val returnDesc: String
)