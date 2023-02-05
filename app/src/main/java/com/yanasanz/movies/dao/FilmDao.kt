package com.yanasanz.movies.dao

import androidx.room.*
import com.yanasanz.movies.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM FilmEntity ORDER BY filmId DESC")
    fun getAll(): Flow<List<FilmEntity>>

    @Query("SELECT * FROM FilmEntity WHERE isFavourite = 1")
    fun getFavourite(): Flow<List<FilmEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filmList: List<FilmEntity>)

    @Query("UPDATE FilmEntity SET description = :description WHERE filmId = :id")
    suspend fun update(id: Int, description: String)

    @Query(
        """
        UPDATE FilmEntity SET
        isFavourite = CASE WHEN isFavourite THEN 0 ELSE 1 END
        WHERE filmId = :id
        """
    )
    suspend fun likeById(id: Int)
}