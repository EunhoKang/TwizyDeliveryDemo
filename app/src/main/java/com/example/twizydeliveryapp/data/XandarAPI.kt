package com.example.twizydeliveryapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface XandarAPI {

    @GET("info")
    suspend fun getInfo(): Response<String>

    companion object {
        private const val BASE_URL = "http://192.168.24.152:9988"

        val xandarAPI = Retrofit.Builder()
            .baseUrl("${BASE_URL}/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(XandarAPI::class.java)
    }
}