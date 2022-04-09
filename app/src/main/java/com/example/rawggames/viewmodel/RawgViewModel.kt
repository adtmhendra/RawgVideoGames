package com.example.rawggames.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawggames.model.State
import com.example.rawggames.model.TopRating
import com.example.rawggames.model.TopRatingResponse
import com.example.rawggames.networking.RawgApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RawgViewModel : ViewModel() {

    private val _listTopRating = MutableLiveData<List<TopRating?>>()
    val listTopRating: LiveData<List<TopRating?>> get() = _listTopRating

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> get() = _state

    init {
        getTopRatingGame()
    }

    private fun getTopRatingGame() {
        viewModelScope.launch {
            _state.value = State.LOADING
            RawgApi.retrofitService.getListTopRating().enqueue(object : Callback<TopRatingResponse> {
                override fun onResponse(
                    call: Call<TopRatingResponse>,
                    response: Response<TopRatingResponse>,
                ) {
                    try {
                        _listTopRating.value = response.body()?.results!!
                        _state.value = State.DONE
                        Log.d("RawgViewModel", "Successfully!..")
                    } catch (e: Exception) {
                        _state.value = State.ERROR
                        Log.e("RawgViewModel", "Failed to load data! ${e.message}")
                    }
                }

                override fun onFailure(call: Call<TopRatingResponse>, t: Throwable) {
                    _state.value = State.ERROR
                    Log.e("RawgViewModel", "Failed to load data! ${t.message}")
                }
            })
        }
    }
}