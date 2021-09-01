package ows.kotlinstudy.movierankapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ows.kotlinstudy.movierankapp.dagger.DaggerRepositoryComponent
import ows.kotlinstudy.movierankapp.response.SimpleMovie
import ows.kotlinstudy.movierankapp.repository.MovieRepository
import javax.inject.Inject

class MovieMainViewModel : ViewModel() {

    private val DEFAULT_TYPE = 1
    private val simpleMovies = MutableLiveData<List<SimpleMovie>>()
    private val loading = MutableLiveData<Boolean>()

    @Inject lateinit var repository : MovieRepository

    init {
        DaggerRepositoryComponent.builder().build().inject(this)
    }

    fun requestSimpleMovieList(type: Int){
        viewModelScope.launch {
            loading.value = true
            val response = repository.requestMovieList(DEFAULT_TYPE)

            if(response.isSuccessful){
                response.body()?.let {
                    simpleMovies.value = it.result
                }
            }
            loading.value = false
        }
    }

   fun getSimpleMovies() = simpleMovies

    fun getLoading() = loading
}