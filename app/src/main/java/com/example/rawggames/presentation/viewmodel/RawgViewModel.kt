package com.example.rawggames.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rawggames.data.response.latestgame.LatestGame
import com.example.rawggames.data.response.searchgame.SearchGame
import com.example.rawggames.data.response.toprating.TopRating
import com.example.rawggames.domain.usecase.HomeUseCase
import com.example.rawggames.domain.usecase.SearchUseCase
import com.example.rawggames.presentation.contract.RawgContract
import com.example.rawggames.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class RawgViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val searchUseCase: SearchUseCase,
) : ViewModel(), RawgContract.UserActionListener {

    private val _listTopRating = MutableLiveData<List<TopRating?>>()
    val listTopRating: LiveData<List<TopRating?>> get() = _listTopRating

    private val _listLatestGame = MutableLiveData<List<LatestGame?>>()
    val listLatestGame: LiveData<List<LatestGame?>> get() = _listLatestGame

    private val _listSearchedGames = MutableLiveData<List<SearchGame?>?>()
    val listSearchedGames: LiveData<List<SearchGame?>?> get() = _listSearchedGames

    private val _state = MutableLiveData<State?>()
    val state: LiveData<State?> get() = _state

    private val _searchState = MutableLiveData<State?>()
    val searchState: LiveData<State?> get() = _searchState

    init {
        getListTopRatingGameData()
        getLatestGameData()
        _searchState.value = State.SUCCESS
    }

    override fun getListTopRatingGameData() {
        _state.value = State.LOADING
        CompositeDisposable().add(
            homeUseCase.getListTopRating()
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

    override fun getLatestGameData() {
        _state.value = State.LOADING
        CompositeDisposable().add(
            homeUseCase.getLatestGame()
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

    override fun getSearchGameData(query: String) {
        _searchState.value = State.LOADING
        CompositeDisposable().add(
            searchUseCase.getSearchGame(query)
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

    override fun isResetSearchGame(isReset: Boolean) {
        if (isReset) {
            _searchState.value = State.SUCCESS
            _listSearchedGames.value = null
        }
    }

    companion object {
        private const val TAG = "RawgViewModel"
    }
}