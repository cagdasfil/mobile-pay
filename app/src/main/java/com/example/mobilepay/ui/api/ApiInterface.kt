package com.example.mobilepay.ui.api

import com.example.mobilepay.BuildConfig
import com.example.mobilepay.ui.screens.pos.model.Amount
import com.example.mobilepay.ui.screens.app.model.Payment
import com.example.mobilepay.ui.screens.app.model.PaymentResult
import com.example.mobilepay.ui.utils.CustomOkHttpClient
import com.example.mobilepay.ui.screens.pos.model.QRData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiInterface {

    @Headers(
        "accept: application/json",
        "content-type: application/json",
        "x-ibm-client-id: ${BuildConfig.CLIENT_ID}",
        "x-ibm-client-secret: ${BuildConfig.CLIENT_SECRET}"
    )
    @POST("get_qr_sale")
    fun getQR(@Body amount: Amount): Call<QRData>


    @Headers(
        "accept: application/json",
        "content-type: application/json",
        "x-ibm-client-id: ${BuildConfig.CLIENT_ID}",
        "x-ibm-client-secret: ${BuildConfig.CLIENT_SECRET}"
    )
    @POST("payment")
    fun startPayment(@Body payment: Payment): Call<PaymentResult>

    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(CustomOkHttpClient.client)
                .baseUrl(BuildConfig.BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}