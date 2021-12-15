package com.example.mobilepay.ui.screens.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobilepay.ui.api.ApiInterface
import com.example.mobilepay.ui.screens.app.model.Payment
import com.example.mobilepay.ui.screens.app.model.PaymentActionList
import com.example.mobilepay.ui.screens.app.model.PaymentInfoList
import com.example.mobilepay.ui.screens.app.model.PaymentResult
import com.example.mobilepay.ui.screens.pos.model.QRData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AppViewModel : ViewModel() {

    var isPaymentSuccessful: MutableLiveData<Boolean?> = MutableLiveData()

    fun startPayment(qrData: QRData) {
        val apiInterface = ApiInterface.create().startPayment(
            createPaymentData(qrData)
        )
        apiInterface.enqueue(object : Callback<PaymentResult> {
            override fun onResponse(
                call: Call<PaymentResult>?,
                response: Response<PaymentResult>?
            ) {
                response?.body()?.let {
                    isPaymentSuccessful.value = true
                }
            }

            override fun onFailure(call: Call<PaymentResult>?, t: Throwable?) {
                isPaymentSuccessful.value = false
            }
        })
    }

    fun clearStatus() {
        isPaymentSuccessful.value = null
    }

    private fun createPaymentData(qrData: QRData): Payment {
        return Payment(
            returnCode = 1000,
            returnDesc = "success",
            receiptMsgCustomer = "beko Campaign/n2018",
            receiptMsgMerchant = "beko Campaign Merchant/n2018",
            QRdata = qrData.QRdata,
            paymentInfoList = listOf(
                PaymentInfoList(
                    paymentProcessorID = 67,
                    paymentActionList = listOf(
                        PaymentActionList(
                            paymentType = 3,
                            amount = 100,
                            currencyID = 949,
                            vatRate = 800
                        )
                    )
                )
            )
        )
    }
}