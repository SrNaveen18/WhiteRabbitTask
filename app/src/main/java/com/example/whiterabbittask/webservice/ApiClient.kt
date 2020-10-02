package com.example.whiterabbittask.webservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://www.mocky.io/v2/"

object ApiClient {
    fun create(): ApiStories {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(ApiStories::class.java)
    }
}