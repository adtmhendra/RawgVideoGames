package com.example.rawggames.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawggames.model.LatestGame
import com.example.rawggames.model.State
import com.example.rawggames.model.TopRating
import com.example.rawggames.networking.RawgApi
import kotlinx.coroutines.launch
import retrofit2.HttpException

private const val TAG = "RawgViewModel"

class RawgViewModel : ViewModel() {

    private val _listTopRating = MutableLiveData<List<TopRating?>>()
    val listTopRating: LiveData<List<TopRating?>> get() = _listTopRating

    private val _listLatestGame = MutableLiveData<List<LatestGame?>>()
    val listLatestGame: LiveData<List<LatestGame?>> get() = _listLatestGame

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    init {
        getTopRatingGame()
        getLatestGame()
    }

    private fun getTopRatingGame() {
        viewModelScope.launch {
            _state.postValue(State.LOADING)
            val response = RawgApi.retrofitService.getListTopRating()
            try {
                if (response.isSuccessful) {
                    _listTopRating.postValue(response.body()?.results!!)
                    _state.postValue(State.SUCCESS)
                    Log.d(TAG, "Success : ${response.code()}")
                } else {
                    _state.postValue(State.FAILED)
                    Log.e(TAG, "Error : ${response.code()}")
                }
            } catch (e: HttpException) {
                _state.postValue(State.FAILED)
                Log.e(TAG, "Error : ${e.message.toString()}")
            }
        }
    }

    private fun getLatestGame() {
        viewModelScope.launch {
            _state.postValue(State.LOADING)
            val response = RawgApi.retrofitService.getLatestGame()
            try {
                if (response.isSuccessful) {
                    _listLatestGame.postValue(response.body()?.results!!)
                    _state.postValue(State.SUCCESS)
                    Log.d(TAG, "Success : ${response.code()}")
                } else {
                    _state.postValue(State.FAILED)
                    Log.e(TAG, "Error : ${response.code()}")
                }
            } catch (e: HttpException) {
                _state.postValue(State.FAILED)
                Log.e(TAG, "Error : ${e.message.toString()}")
            }
        }
    }
}