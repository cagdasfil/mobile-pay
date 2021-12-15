package com.example.mobilepay.ui.screens.pos.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobilepay.ui.screens.pos.model.Amount
import com.example.mobilepay.ui.api.ApiInterface
import com.example.mobilepay.ui.screens.pos.model.QRData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "PosViewModel"


class PosViewModel : ViewModel() {

    var qrData: MutableLiveData<QRData?> = MutableLiveData()

    fun getQR(amount: Int) {
        val apiInterface = ApiInterface.create().getQR(Amount(amount))
        apiInterface.enqueue(object : Callback<QRData> {
            override fun onResponse(call: Call<QRData>?, response: Response<QRData>?) {
                response?.body()?.let { responseQRData ->
                    qrData.value = responseQRData
                }
            }

            override fun onFailure(call: Call<QRData>?, t: Throwable?) {
                Log.e(TAG, "")
            }
        })
    }

    fun clearQRData() {
        qrData.value = null
    }
}