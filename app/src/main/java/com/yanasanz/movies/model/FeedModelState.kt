package com.yanasanz.movies.model

data class FeedModelState (
    val loading: Boolean = false,
    val error: Boolean = false,
    var noMatchesFound: Boolean = false
)