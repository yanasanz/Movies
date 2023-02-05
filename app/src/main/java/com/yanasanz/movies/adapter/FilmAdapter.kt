package com.yanasanz.movies.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yanasanz.movies.databinding.CardFilmBinding
import com.yanasanz.movies.dto.Film
import com.yanasanz.movies.utils.load

interface FilmInteractionListener {
    fun onOpenDetails(film: Film) {}
    fun onLike(film: Film) {}
}

class FilmAdapter (private val onInteractionListener: FilmInteractionListener) : ListAdapter<Film,
        FilmViewHolder>(FilmDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = CardFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val job = getItem(position)
        holder.bind(job)
    }
}

class FilmViewHolder(
    private val binding: CardFilmBinding,
    private val listener: FilmInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Film) {

        binding.apply {
            poster.load(film.posterUrlPreview)
            name.text = film.nameRu
            val genreAndYearFormat = "${film.genres.first().genre} (${film.year})"
            genreAndYear.text = genreAndYearFormat
            favourite.visibility = if (film.isFavourite) VISIBLE else GONE

            root.setOnClickListener {
                listener.onOpenDetails(film)
            }

            root.setOnLongClickListener{
                listener.onLike(film)
                return@setOnLongClickListener true
            }
        }
    }
}

class FilmDiffCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}