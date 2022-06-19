package com.example.certificates.ui.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.certificates.api.Certificate
import com.example.certificates.api.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val certificateMutableLiveData = MutableLiveData<List<Certificate>>()

    fun getCertificateData(context: Context) {
        Client.apiServices.getCertificates().enqueue(object : Callback<List<Certificate>> {
            override fun onResponse(
                call: Call<List<Certificate>>,
                response: Response<List<Certificate>>
            ) {
                certificateMutableLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<Certificate>>, t: Throwable) {
                Toast.makeText(context, "Error No Internet Connection", Toast.LENGTH_LONG).show()
            }
        })
    }

    companion object {
        private const val BASE_URL = "https://m7madmagdy.github.io/pages/"
    }
}