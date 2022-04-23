package com.example.rawggames.presentation.contract

class RawgContract {

    interface UserActionListener {
        fun getListTopRatingGameData()
        fun getLatestGameData()
        fun getSearchGameData(query: String)
        fun isResetSearchGame(isReset: Boolean)
    }
}