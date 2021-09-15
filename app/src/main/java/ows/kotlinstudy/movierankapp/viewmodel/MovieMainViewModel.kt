package ows.kotlinstudy.movierankapp.viewmodel

import android.util.Log
import android.widget.Button
import androidx.databinding.BindingAdapter
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
    private val simpleMoviesLiveData = MutableLiveData<List<SimpleMovie>>()
    private var simpleMovies = listOf<SimpleMovie>()
    private val loadingLiveData = MutableLiveData<Boolean>()
    private var sortNameLiveData = MutableLiveData<String>()

    fun requestSimpleMovieList(type: Int = DEFAULT_TYPE) {
        Log.d("msg","requestSimpleMovieList ${type}")
        viewModelScope.launch {
            loadingLiveData.value = true
            val response = repository.requestMovieList(type)

            if (response.code == 1) {
                response.data?.let {
                    simpleMovies = it.result
                    simpleMoviesLiveData.value = simpleMovies
                }
            }

            when(type){
                1 -> sortNameLiveData.value = "예매율순"
                2 -> sortNameLiveData.value = "큐레이션"
                3 -> sortNameLiveData.value = "상영예정"
            }

            loadingLiveData.value = false
        }
    }

    fun getSimpleMoviesLiveData() = simpleMoviesLiveData

    fun getLoadingLiveData() = loadingLiveData

    fun getSortNameLiveData() = sortNameLiveData
}