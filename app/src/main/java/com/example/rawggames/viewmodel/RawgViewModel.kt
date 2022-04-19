package com.example.rawggames.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawggames.model.LatestGame
import com.example.rawggames.model.SearchedGame
import com.example.rawggames.model.State
import com.example.rawggames.model.TopRating
import com.example.rawggames.networking.RawgApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RawgViewModel @Inject constructor(private val apiService: RawgApiService) : ViewModel() {

    companion object {
        private const val TAG = "RawgViewModel"
    }

    private val _listTopRating = MutableLiveData<List<TopRating?>>()
    val listTopRating: LiveData<List<TopRating?>> get() = _listTopRating

    private val _listLatestGame = MutableLiveData<List<LatestGame?>>()
    val listLatestGame: LiveData<List<LatestGame?>> get() = _listLatestGame

    private val _listSearchedGames = MutableLiveData<List<SearchedGame?>?>()
    val listSearchedGames: LiveData<List<SearchedGame?>?> get() = _listSearchedGames

    private val _state = MutableLiveData<State?>()
    val state: LiveData<State?> get() = _state

    private val _searchState = MutableLiveData<State?>()
    val searchState: LiveData<State?> get() = _searchState

    init {
        getTopRatingGame()
        getLatestGame()
        _searchState.value = State.SUCCESS
    }

    private fun getTopRatingGame() {
        viewModelScope.launch {
            _state.value = State.LOADING
            try {
                val response = apiService.getListTopRating()
                if (response.isSuccessful) {
                    _listTopRating.postValue(response.body()?.results!!)
                    _state.value = State.SUCCESS
                    Log.d(TAG, "Success : ${response.code()}")
                } else {
                    _state.value = State.ERROR
                    Log.e(TAG, "Error : ${response.code()}")
                }
            } catch (e: Exception) {
                _state.value = State.ERROR
                Log.e(TAG, "Error : ${e.message.toString()}")
            }
        }
    }

    private fun getLatestGame() {
        viewModelScope.launch {
            _state.value = State.LOADING
            try {
                val response = apiService.getLatestGame()
                if (response.isSuccessful) {
                    _listLatestGame.postValue(response.body()?.results!!)
                    _state.value = State.SUCCESS
                    Log.d(TAG, "Success : ${response.code()}")
                } else {
                    _state.value = State.ERROR
                    Log.e(TAG, "Error : ${response.code()}")
                }
            } catch (e: Exception) {
                _state.value = State.ERROR
                Log.e(TAG, "Error : ${e.message.toString()}")
            }
        }
    }

    fun getSearchedGames(query: String) {
        viewModelScope.launch {
            _searchState.value = State.LOADING
            try {
                val response = apiService.getSearchedGames(query)
                if (response.isSuccessful) {
                    val listGame = response.body()?.results!!
                    if (listGame.isNotEmpty()) {
                        _listSearchedGames.value = listGame
                        _searchState.value = State.SUCCESS
                        Log.d(TAG, "Success : ${response.code()}")
                    } else {
                        _searchState.value = State.FAILED
                        Log.d(TAG, "Failed : Data doesn't exists")
                    }
                } else {
                    _searchState.value = State.ERROR
                    Log.e(TAG, "Error : ${response.code()}")
                }
            } catch (e: Exception) {
                _searchState.value = State.ERROR
                Log.e(TAG, "Error : ${e.message.toString()}")
            }
        }
    }

    fun isResetSearchGame(isReset: Boolean) {
        if (isReset) {
            _listSearchedGames.value = null
            _searchState.value = State.SUCCESS
        }
    }
}