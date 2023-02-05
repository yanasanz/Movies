package com.yanasanz.movies.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.yanasanz.movies.R
import com.yanasanz.movies.adapter.FilmAdapter
import com.yanasanz.movies.adapter.FilmInteractionListener
import com.yanasanz.movies.databinding.FragmentFeedBinding
import com.yanasanz.movies.dto.Film
import com.yanasanz.movies.viewmodel.FilmViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private var binding: FragmentFeedBinding? = null
    private val viewModel: FilmViewModel by activityViewModels()
    lateinit var adapter: FilmAdapter
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val search = menu.findItem(R.id.menu_search)
        searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    viewModel.data.observe(viewLifecycleOwner) {
                        val searchText = newText.lowercase(Locale.getDefault())
                        val newArray = it.filter {
                            it.nameRu?.lowercase(Locale.getDefault())?.contains(searchText) ?: false
                        }
                        if (newArray.isEmpty()) {
                            viewModel.dataState.value?.noMatchesFound
                        }
                        adapter.submitList(newArray)
                    }
                } else {
                    viewModel.data.observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
                }
                return true
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)

        adapter = FilmAdapter(object : FilmInteractionListener {
            override fun onLike(film: Film) {
                viewModel.likeById(film.filmId)
            }

            override fun onOpenDetails(film: Film) {
                viewModel.getFilmDescription(film.filmId)
                val bundle = Bundle()
                bundle.putParcelable("film", film)
                findNavController().navigate(
                    R.id.action_feedFragment_to_filmDetailsFragment,
                bundle)
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { films ->
            adapter.submitList(films)
        }
        viewModel.dataState.observe(viewLifecycleOwner) { state ->
            binding.progress.isVisible = state.loading
            binding.errorGroup.isVisible = state.error
            binding.noMatchesFound.isVisible = state.noMatchesFound
        }

        binding.retryButton.setOnClickListener {
            viewModel.getData()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}