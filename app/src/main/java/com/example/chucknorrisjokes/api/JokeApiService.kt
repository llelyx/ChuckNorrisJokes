package com.example.chucknorrisjokes.api

import androidx.lifecycle.MutableLiveData
import com.example.chucknorrisjokes.Joke
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Response

interface JokeApiService{

//    @GET("jokes/random")
//    fun giveMeAJoke(): Single<Joke> {
//        val myApi = JokeApiServiceFactory.giveMeApi()
//        return myApi.giveMeAJoke()
//    }
    @GET("jokes/random")
    fun giveMeAJoke(): Single<Joke>
}