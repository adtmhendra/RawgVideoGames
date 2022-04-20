package com.example.rawggames.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rawggames.model.LatestGame
import com.example.rawggames.model.SearchedGame
import com.example.rawggames.model.State
import com.example.rawggames.model.TopRating
import com.example.rawggames.networking.RawgApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RawgViewModel @Inject constructor(private val rawgApiService: RawgApiService) : ViewModel() {

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
        _state.value = State.LOADING
        CompositeDisposable().add(
            rawgApiService.getListTopRating()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listTopRatingData ->
                    _listTopRating.value = listTopRatingData.results!!
                    _state.value = State.SUCCESS
                    Log.d(TAG, "Success : ${listTopRatingData.results}")
                }, { error ->
                    _state.value = State.ERROR
                    Log.e(TAG, "Error : ${error.message.toString()}")
                })
        )
    }

    private fun getLatestGame() {
        _state.value = State.LOADING
        CompositeDisposable().add(
            rawgApiService.getLatestGame()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listLatestGameData ->
                    _listLatestGame.value = listLatestGameData.results!!
                    _state.value = State.SUCCESS
                    Log.d(TAG, "Success : ${listLatestGameData.results}")
                }, { error ->
                    _state.value = State.ERROR
                    Log.e(TAG, "Error : ${error.message.toString()}")
                })
        )
    }

    fun getSearchedGames(query: String) {
        _searchState.value = State.LOADING
        CompositeDisposable().add(
            rawgApiService.getSearchedGames(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listSearchedGameData ->
                    val listSearchedGame = listSearchedGameData.results!!
                    if (listSearchedGame.isNotEmpty()) {
                        _listSearchedGames.value = listSearchedGame
                        _searchState.value = State.SUCCESS
                        Log.d(TAG, "Success : $listSearchedGame")
                    } else {
                        _listSearchedGames.value = null
                        _searchState.value = State.FAILED
                        Log.d(TAG, "Failed : Data doesn't exists")
                    }
                }, { error ->
                    _searchState.value = State.ERROR
                    Log.e(TAG, "Error : ${error.message.toString()}")
                })
        )
    }

    fun isResetSearchGame(isReset: Boolean) {
        if (isReset) {
            _searchState.value = State.SUCCESS
            _listSearchedGames.value = null
        }
    }
}