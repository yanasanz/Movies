package com.yanasanz.movies.repository

import com.yanasanz.movies.dto.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    val data: Flow<List<Film>>
    val favourite: Flow<List<Film>>
    suspend fun getData()
    suspend fun likeById(id: Int)
    suspend fun getFilmDescription(id: Int)
}