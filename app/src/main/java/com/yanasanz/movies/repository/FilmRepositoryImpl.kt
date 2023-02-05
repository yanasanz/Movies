package com.yanasanz.movies.repository

import com.google.gson.Gson
import com.yanasanz.movies.api.ApiService
import com.yanasanz.movies.dao.FilmDao
import com.yanasanz.movies.dto.Film
import com.yanasanz.movies.entity.FilmEntity
import com.yanasanz.movies.entity.toDto
import com.yanasanz.movies.entity.toEntity
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val filmDao: FilmDao,
    private val apiService: ApiService
) : FilmRepository {

    override val data: Flow<List<Film>> = filmDao.getAll().map { it.toDto() }

    override val favourite: Flow<List<Film>> = filmDao.getFavourite().map { it.toDto() }

    override suspend fun getData(){
        try {
            val response = apiService.getFilmsTop()
            if (!response.isSuccessful) {
                throw Error()
            }
            val body = response.body()?.films ?: throw Error()
            filmDao.insert(body.toEntity())
        } catch (e: Exception) {
            throw Error()
        }
    }

    override suspend fun getFilmDescription(id: Int){
        try {
            val response = apiService.getFilmInfo(id)
            if (!response.isSuccessful) {
                throw Error()
            }
            val body = response.body() ?: throw Error()
            body.description?.let { filmDao.update(body.kinopoiskId, it) }
        } catch (e: Exception) {
            throw Error()
        }
    }

    override suspend fun likeById(id: Int) {
        filmDao.likeById(id)
    }
}