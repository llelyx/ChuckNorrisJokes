package com.example.chucknorrisjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokes.api.JokeApiService
import com.example.chucknorrisjokes.api.JokeApiServiceFactory
import com.example.chucknorrisjokes.databinding.ActivityMainBinding
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<JokeAdapter.JokeViewHolder>? = null

    // all bindings are replacing kotlin.android.extensions which didn't work
    private lateinit var binding: ActivityMainBinding
    //private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        layoutManager = LinearLayoutManager(this)

        binding.recyclerView.layoutManager = layoutManager

        adapter = JokeAdapter()
        binding.recyclerView.adapter = adapter

        val service: JokeApiService = JokeApiServiceFactory.service
        val jokeService: JokeApiService = JokeApiServiceFactory.buildService(service)
        val joke: Single<Joke> = jokeService.giveMeAJoke()
        val myCompositeDisposable = CompositeDisposable()

        myCompositeDisposable.add(joke.subscribeOn(Schedulers.io())
            .subscribeBy(
            onError = {Log.d("Response", it.stackTraceToString())},
            onSuccess = {Log.d("Response", it.value)}
            )
        )

        //myCompositeDisposable.clear()

    }
}