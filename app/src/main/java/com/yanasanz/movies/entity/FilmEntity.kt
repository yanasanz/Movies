package com.yanasanz.movies.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.yanasanz.movies.dao.Converters
import com.yanasanz.movies.dto.Country
import com.yanasanz.movies.dto.Film
import com.yanasanz.movies.dto.Genre

@Entity
@TypeConverters(Converters::class)

data class FilmEntity(
    @PrimaryKey
    val filmId: Int,
    val nameRu: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val year: Int?,
    val description: String?,
    val genres: List<Genre>,
    val countries: List<Country>,
    var isFavourite: Boolean,
) {
    fun toDto() = Film(
        filmId, nameRu, posterUrl, posterUrlPreview, year, description,
        genres, countries, isFavourite
    )

    companion object {
        fun fromDto(dto: Film) =
            FilmEntity(
                dto.filmId, dto.nameRu, dto.posterUrl, dto.posterUrlPreview, dto.year,
                dto.description, dto.genres, dto.countries,
                dto.isFavourite
            )
    }
}

fun List<Film>.toEntity(): List<FilmEntity> = map(FilmEntity::fromDto)
fun List<FilmEntity>.toDto(): List<Film> = map(FilmEntity::toDto)
