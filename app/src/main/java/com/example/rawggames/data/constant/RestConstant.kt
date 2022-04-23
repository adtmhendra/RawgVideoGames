package com.example.rawggames.data.constant

object RestConstant {

    object RawgApi {
        private const val BASE_API_KEY = "4d601705c8324648b378d5e18e98d78e"
        const val BASE_URL = "https://api.rawg.io/api/"

        const val listTopRating =
            "games?key=$BASE_API_KEY&page_size=10&ordering=-rating&platforms=4&page=1"
        const val latestGame =
            "games?key=$BASE_API_KEY&page_size=10&ordering=-released&platforms=4&page=1&dates=2021-12-01,2021-12-31"
        const val searchedGame = "games?key=$BASE_API_KEY&page_size=10&platforms=4&page=1"
    }
}