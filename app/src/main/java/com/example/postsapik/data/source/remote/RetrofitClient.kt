package com.example.postsapik.data.source.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
const val Base_URL = "https://jsonplaceholder.typicode.com/";
object RetrofitClient {
    private var retrofit :Retrofit?=null

    fun getService(): WebService? {
        if (retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit?.create(WebService::class.java)
    }

}