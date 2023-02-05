package com.yanasanz.movies.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FilmRepositoryModule {

    @Binds
    @Singleton
    fun bindRepositoryImpl(filmRepository: FilmRepositoryImpl): FilmRepository
}