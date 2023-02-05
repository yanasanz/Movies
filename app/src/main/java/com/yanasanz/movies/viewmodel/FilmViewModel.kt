package com.yanasanz.movies.viewmodel

import androidx.lifecycle.*
import com.yanasanz.movies.dto.Film
import com.yanasanz.movies.model.FeedModelState
import com.yanasanz.movies.repository.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FilmViewModel @Inject constructor(
    private val repository: FilmRepository
) : ViewModel() {

    private val _data = repository.data.asLiveData(Dispatchers.Default)
    val data: LiveData<List<Film>>
        get() = _data

    private val _dataState = MutableLiveData<FeedModelState>()
    val dataState: LiveData<FeedModelState>
        get() = _dataState

    private val _favourite = repository.favourite.asLiveData(Dispatchers.Default)
    val favourite: LiveData<List<Film>>
        get() = _favourite

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            try {
                repository.getData()
                _dataState.value = FeedModelState(loading = true)
                _dataState.value = FeedModelState()
            } catch (e: Exception) {
                _dataState.value = FeedModelState(error = true)
            }
        }
    }

    fun likeById(id: Int) = viewModelScope.launch {
        repository.likeById(id)
    }

    fun getFilmDescription(id: Int) {
        viewModelScope.launch {
        }
    }

}