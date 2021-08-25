package ows.kotlinstudy.movierankapp.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ows.kotlinstudy.movierankapp.data.MovieListResponse
import ows.kotlinstudy.movierankapp.data.SimpleMovie
import ows.kotlinstudy.movierankapp.repo.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieMainViewModel : ViewModel() {

    private val simpleMovies = MutableLiveData<List<SimpleMovie>>()
    private val loading = MutableLiveData<Boolean>()
    private val repository by lazy { MovieRepository() }

    fun loadSimpleMovieList(type: Int){
        loading.value = true
        repository.requestMovieList(type).enqueue(object: Callback<MovieListResponse>{
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        simpleMovies.value = it.result
                    }
                }
                loading.value = false
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {

            }
        })
    }

    fun getSimpleMovies() = simpleMovies
    fun getLoading() = loading
}