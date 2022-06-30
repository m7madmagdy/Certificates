package com.example.certificates.ui.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.certificates.api.Certificate
import com.example.certificates.api.Client
import com.example.certificates.offline.RealmConfiguration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val certificateMutableLiveData = MutableLiveData<ArrayList<Certificate>>()
    var certificateList: ArrayList<Certificate> = ArrayList()

    fun getCertificateData(context: Context) {
        val realm = RealmConfiguration(context).realm

        Client.apiServices.getCertificates().enqueue(object : Callback<ArrayList<Certificate>> {
            override fun onResponse(
                call: Call<ArrayList<Certificate>>,
                response: Response<ArrayList<Certificate>>
            ) {
                certificateMutableLiveData.value = response.body()
                certificateList = certificateMutableLiveData.value!!
                realm.executeTransaction {
                    try {
                        for (certificate in response.body()!!) {
                            val category =
                                realm.createObject(Certificate::class.java, certificate.id)
                            category.name = certificate.name
                            category.image = certificate.image
                        }
                    } catch (e: Exception) {
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Certificate>>, t: Throwable) {
                val categories = realm.where(Certificate::class.java).findAll()
                certificateList = ArrayList()
                certificateList.addAll(categories)
                certificateMutableLiveData.value = certificateList
            }
        })
    }
}