package com.example.certificates.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("certificate.json")
    fun getCertificates(): Call<ArrayList<Certificate>>
}