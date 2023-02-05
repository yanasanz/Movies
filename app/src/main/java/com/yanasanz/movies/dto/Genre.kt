package com.yanasanz.movies.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    val genre: String
): Parcelable
