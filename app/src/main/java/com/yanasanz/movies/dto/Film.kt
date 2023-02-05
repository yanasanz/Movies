package com.yanasanz.movies.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val filmId: Int = 0,
    val nameRu: String? = null,
    val posterUrl: String? = null,
    val posterUrlPreview: String? = null,
    val year: Int? = null,
    val description: String? = null,
    val genres: List<Genre> = emptyList(),
    val countries: List<Country> = emptyList(),
    var isFavourite: Boolean = false,
): Parcelable