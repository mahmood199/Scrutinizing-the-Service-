package com.example.scrutinizing_the_service.v2


object ScreenName {
    const val AUDIO_LIST = "audio-list"
    const val AUDIO_PLAYER = "audio-player"
    const val MAIN = "main"
    const val SEARCH_HISTORY = "search_history"
    const val SEARCH_RESULT = "search_result"
    const val LANDING_PAGE = "landing_page"
    const val SETTINGS_PAGE = "settings"
}


sealed class Screen(val name: String) {
    data object AudioList : Screen(name = ScreenName.AUDIO_LIST)
    data object AudioPlayer : Screen(name = ScreenName.AUDIO_PLAYER)
    data object Main : Screen(name = ScreenName.MAIN)
    data object SearchHistory : Screen(name = ScreenName.SEARCH_HISTORY)
    data object SearchResult : Screen(name = ScreenName.SEARCH_RESULT)
    data object LandingPage : Screen(name = ScreenName.LANDING_PAGE)
    data object SettingsPage : Screen(name = ScreenName.SETTINGS_PAGE)
}