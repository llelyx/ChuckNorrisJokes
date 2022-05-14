package com.example.chucknorrisjokes.api

import com.example.chucknorrisjokes.util.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object JokeApiServiceFactory {

    val builder: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    val service: JokeApiService = builder.create(JokeApiService::class.java)

    fun<JokeApiService> buildService(service: JokeApiService):
            JokeApiService {return service}
}