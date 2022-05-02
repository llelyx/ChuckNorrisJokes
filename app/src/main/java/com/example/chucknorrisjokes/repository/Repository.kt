package com.example.chucknorrisjokes.repository

import com.example.chucknorrisjokes.Joke
import com.example.chucknorrisjokes.api.JokeApiServiceFactory
import io.reactivex.Single
import retrofit2.Response

class Repository {

    suspend fun giveMeAJoke(): Response<Joke> {
        return JokeApiServiceFactory.api.giveMeAJoke()
    }
}