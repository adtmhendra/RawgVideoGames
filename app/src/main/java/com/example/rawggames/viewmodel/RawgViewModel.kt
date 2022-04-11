package com.example.rawggames.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawggames.model.*
import com.example.rawggames.networking.RawgApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RawgViewModel : ViewModel() {

    private val _listTopRating = MutableLiveData<List<TopRating?>>()
    val listTopRating: LiveData<List<TopRating?>> get() = _listTopRating

    private val _listLatestGame = MutableLiveData<List<LatestGame?>>()
    val listLatestGame: LiveData<List<LatestGame?>> get() = _listLatestGame

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    init {
        getTopRatingGame()
    }

    private fun getTopRatingGame() {
        viewModelScope.launch {
            _state.value = State.LOADING
            RawgApi.retrofitService.getListTopRating()
                .enqueue(object : Callback<TopRatingResponse> {
                    override fun onResponse(
                        call: Call<TopRatingResponse>,
                        response: Response<TopRatingResponse>,
                    ) {
                        if (response.isSuccessful) {
                            _listTopRating.value = response.body()?.results!!
                            _state.value = State.DONE
                            Log.d("RawgViewModel", "Top rating status : ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<TopRatingResponse>, t: Throwable) {
                        _state.value = State.ERROR
                        Log.e("RawgViewModel", "Top rating error : ${t.message}")
                    }
                })

            RawgApi.retrofitService.getLatestGame().enqueue(object : Callback<LatestGameResponse> {
                override fun onResponse(
                    call: Call<LatestGameResponse>,
                    response: Response<LatestGameResponse>,
                ) {
                    if (response.isSuccessful) {
                        _listLatestGame.value = response.body()?.results!!
                        _state.value = State.DONE
                        Log.d("RawgViewModel", "Latest game status : ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<LatestGameResponse>, t: Throwable) {
                    _state.value = State.ERROR
                    Log.e("RawgViewModel", "Latest game error : ${t.message}")
                }
            })
        }
    }
}