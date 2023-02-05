package com.yanasanz.movies.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yanasanz.movies.dao.Converters
import com.yanasanz.movies.dao.FilmDao
import com.yanasanz.movies.entity.FilmEntity

@Database(entities = [FilmEntity::class], version = 4)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}