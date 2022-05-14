package com.example.chucknorrisjokes

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorrisjokes.api.JokeApiService
import com.example.chucknorrisjokes.api.JokeApiServiceFactory
import com.example.chucknorrisjokes.databinding.ActivityMainBinding
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    // all bindings are replacing viewModels because kotlin.android.extensions didn't work
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager = LinearLayoutManager(this)
        val adapter = JokeAdapter()

        val service: JokeApiService = JokeApiServiceFactory.service
        val jokeService: JokeApiService = JokeApiServiceFactory.buildService(service)
        val joke: Single<Joke> = jokeService.giveMeAJoke()
        val myCompositeDisposable = CompositeDisposable()

        myCompositeDisposable.add(joke
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {Log.d("Response", it.stackTraceToString())},
                onSuccess = {
                    Log.d("Response", it.value)
                    adapter.updateJokes(it)
                    Log.d("Response", adapter.jokes.toString())
                    binding.recyclerView.layoutManager = layoutManager
                    binding.recyclerView.adapter = adapter
                }
            )
        )

        binding.buttonAddJoke.setOnClickListener{
            myCompositeDisposable.add(joke
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {Log.d("Response", it.stackTraceToString())},
                    onSuccess = {
                        Log.d("Response", it.value)
                        adapter.updateJokes(it)
                        Log.d("Response", adapter.jokes.toString())
                        binding.recyclerView.layoutManager = layoutManager
                        binding.recyclerView.adapter = adapter
                    }
                )
            )
        }



        //Log.d("Response", adapter.jokes.toString())
        //myCompositeDisposable.clear()

    }
}