package com.yanasanz.movies.api

import com.yanasanz.movies.dto.Film
import com.yanasanz.movies.dto.FilmResponse
import com.yanasanz.movies.dto.ListOfFilms
import com.yanasanz.movies.utils.Constants.FILMS_TOP_END_POINT
import com.yanasanz.movies.utils.Constants.FILM_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(FILMS_TOP_END_POINT)
    suspend fun getFilmsTop(): Response<ListOfFilms>

    @GET("${FILM_END_POINT}/{id}")
    suspend fun getFilmInfo(@Path("id") id: Int): Response<FilmResponse>

}