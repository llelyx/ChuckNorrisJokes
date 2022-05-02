package com.example.chucknorrisjokes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokes.repository.Repository
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Joke>> = MutableLiveData()

    fun giveMeAJoke() {
        viewModelScope.launch {
            val response = repository.giveMeAJoke()
            myResponse.value = response
        }
    }
}