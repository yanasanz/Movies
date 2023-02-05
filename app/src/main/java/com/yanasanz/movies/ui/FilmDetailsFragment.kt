package com.yanasanz.movies.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yanasanz.movies.databinding.FragmentFilmDetailsBinding
import com.yanasanz.movies.dto.Film
import com.yanasanz.movies.utils.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmDetailsFragment: Fragment() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilmDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        val film = arguments?.getParcelable("film", Film::class.java)

        (activity as AppCompatActivity?)?.supportActionBar?.title = film?.nameRu
        binding.posterBigSize.load(film?.posterUrl)
        binding.name.text = film?.nameRu
        var genres = "Жанры: "
        film?.genres?.forEach { genres += "${it.genre}, " }
        val genresText = genres.dropLastWhile { !it.isLetter() }
        binding.genres.text = genresText
        var countries = "Страны: "
        film?.countries?.forEach { countries += "${it.country}, " }
        val countriesText = countries.dropLastWhile { !it.isLetter() }
        binding.countries.text = countriesText

        return binding.root
    }
}