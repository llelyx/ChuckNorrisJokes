package com.example.chucknorrisjokes

import android.R
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chucknorrisjokes.api.JokeApiService
import com.example.chucknorrisjokes.api.JokeApiServiceFactory
import com.example.chucknorrisjokes.databinding.ActivityMainBinding
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    // all bindings are replacing viewModels because kotlin.android.extensions didn't work
    private lateinit var binding: ActivityMainBinding
    private var progressBar: ProgressBar? = null

    @RequiresApi(Build.VERSION_CODES.Q)
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

        fun getAJoke(){
            binding.progressBar.visibility = View.VISIBLE
            myCompositeDisposable.add(joke
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {Log.d("Response", it.stackTraceToString())},
                    onSuccess = {
                        adapter.updateJokes(it)
                        binding.recyclerView.layoutManager = layoutManager
                        binding.recyclerView.adapter = adapter
                        binding.progressBar.visibility = View.INVISIBLE
                    }
                )
            )
        }

        getAJoke()
        binding.buttonAddJoke.setOnClickListener{getAJoke()}



        //Log.d("Response", adapter.jokes.toString())
        //myCompositeDisposable.clear()

    }
}