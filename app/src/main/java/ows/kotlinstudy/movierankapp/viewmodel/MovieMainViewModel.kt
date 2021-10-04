package ows.kotlinstudy.movierankapp.viewmodel

import androidx.lifecycle.LiveData
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

    private val simpleMoviesMutableLiveData = MutableLiveData<List<SimpleMovie>>()
    val simpleMoviseLiveData: LiveData<List<SimpleMovie>>
        get() = simpleMoviesMutableLiveData
    private var simpleMovies = listOf<SimpleMovie>()

    private val loadingMutableLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = loadingMutableLiveData

    private var sortNameMutableLiveData = MutableLiveData<String>()
    val sortNameLiveData: LiveData<String>
        get() = sortNameMutableLiveData

    fun requestSimpleMovieList(type: Int = DEFAULT_TYPE) {
        viewModelScope.launch {
            loadingMutableLiveData.value = true
            val response = repository.requestSimpleMovieList(type)

            if (response.code == 1) {
                response.data?.let {
                    simpleMovies = it.result
                    simpleMoviesMutableLiveData.value = simpleMovies
                }
            }

            when (type) {
                1 -> sortNameMutableLiveData.value = "예매율순"
                2 -> sortNameMutableLiveData.value = "큐레이션"
                3 -> sortNameMutableLiveData.value = "상영예정"
            }

            loadingMutableLiveData.value = false
        }
    }

}