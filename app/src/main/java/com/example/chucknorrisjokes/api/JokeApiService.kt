package com.example.chucknorrisjokes.api

import com.example.chucknorrisjokes.Joke
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.Response

interface JokeApiService{

    @GET("jokes/random")
    suspend fun giveMeAJoke(): Response<Joke>
}