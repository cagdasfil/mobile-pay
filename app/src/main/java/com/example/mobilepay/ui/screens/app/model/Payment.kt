package com.example.mobilepay.ui.screens.app.model

import kotlinx.serialization.Serializable

@Serializable
data class Payment(
    val returnCode: Int,
    val returnDesc: String,
    val receiptMsgCustomer: String,
    val receiptMsgMerchant: String,
    val paymentInfoList: List<PaymentInfoList>,
    val QRdata: String
)

@Serializable
data class PaymentInfoList(
    val paymentProcessorID: Int,
    val paymentActionList: List<PaymentActionList>,
)

@Serializable
data class PaymentActionList(
    val paymentType: Int,
    val amount: Int,
    val currencyID: Int,
    val vatRate: Int
)