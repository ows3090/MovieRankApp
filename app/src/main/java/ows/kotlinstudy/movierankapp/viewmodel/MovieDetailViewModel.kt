package ows.kotlinstudy.movierankapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ows.kotlinstudy.movierankapp.repository.MovieRepository
import ows.kotlinstudy.movierankapp.response.Comment
import ows.kotlinstudy.movierankapp.response.Movie
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {

    private val movieMutableLiveData = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie>
        get() = movieMutableLiveData

    private var commentListMutableLiveData = MutableLiveData<List<Comment>>()
    val commentListLiveData: LiveData<List<Comment>>
        get() = commentListMutableLiveData
    private var commentList = listOf<Comment>()

    fun requestMovieDetail(id: Int) {
        viewModelScope.launch {
            val response = repository.requestMovieDetail(id)

            if (response.code == 1) {
                response.data?.let {
                    it.result?.let { movies ->
                        movieMutableLiveData.value = movies.first()
                    }
                }
            }
        }
    }

    fun requestCommentList(id: Int) {
        viewModelScope.launch {
            val response = repository.requestCommentList(id)

            if (response.code == 1) {
                response.data?.let {
                    it.result?.let { comments ->
                        commentList = comments
                        commentListMutableLiveData.value = commentList
                    }
                }
            }
        }
    }

}