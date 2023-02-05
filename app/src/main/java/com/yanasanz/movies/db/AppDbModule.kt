package com.yanasanz.movies.db

import android.content.Context
import androidx.room.Room
import com.yanasanz.movies.dao.FilmDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppDbModule {

    @Provides
    @Singleton
    fun provideAppDb(@ApplicationContext context: Context): AppDb = Room.databaseBuilder(
        context,
        AppDb::class.java,
        "App.db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideFilmDao(appDB: AppDb): FilmDao = appDB.filmDao()
}