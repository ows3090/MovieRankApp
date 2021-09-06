package ows.kotlinstudy.movierankapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ows.kotlinstudy.movierankapp.repository.MovieRepository
import ows.kotlinstudy.movierankapp.response.SimpleMovie
import javax.inject.Inject

class MovieMainViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {
    private val DEFAULT_TYPE = 1
    private val simpleMovies = MutableLiveData<List<SimpleMovie>>()
    private val loading = MutableLiveData<Boolean>()

    fun requestSimpleMovieList(type: Int = DEFAULT_TYPE) {
        viewModelScope.launch {
            loading.value = true
            val response = repository.requestMovieList(DEFAULT_TYPE)

            if (response.code == 1) {
                response.data?.let {
                    simpleMovies.value = it.result
                }
            }
            loading.value = false
        }
    }

    fun getSimpleMovies() = simpleMovies

    fun getLoading() = loading

}