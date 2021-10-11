package ows.kotlinstudy.movierankapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ows.kotlinstudy.movierankapp.repository.request.MovieLikeAndDisLikeRequestBody
import ows.kotlinstudy.movierankapp.repository.response.MovieCommentResponse
import ows.kotlinstudy.movierankapp.repository.response.MovieDetailResponse
import ows.kotlinstudy.movierankapp.repository.response.MovieLikeAndDisLikeResponse
import ows.kotlinstudy.movierankapp.repository.response.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val movieService: MovieService
) {
    suspend fun fecthSimpleMovieList(type: Int): Response<MovieListResponse> {
        return withContext(Dispatchers.IO) {
            movieService.requestMovieListForCoroutine(type)
        }
    }

    suspend fun fetchMovieDetail(id: Int): Response<MovieDetailResponse> {
        return withContext(Dispatchers.IO) {
            movieService.requestMovieDetailForCoroutine(id)
        }
    }

    suspend fun fecthCommentList(id: Int): Response<MovieCommentResponse> {
        return withContext(Dispatchers.IO) {
            movieService.requestMovieCommentListForCoroutine(id)
        }
    }

    suspend fun fetchMovieLike(
        id: Int,
        likeyn: String
    ): Response<MovieLikeAndDisLikeResponse> {
        return withContext(Dispatchers.IO) {
            movieService.requestMovieIncreaseLikeDisLikeForCoroutine(
                HashMap<String,String>().apply {
                    put("id",id.toString())
                    put("likeyn",likeyn)
                }
            )
        }
    }

    suspend fun fetchMovieDisLike(
        id: Int,
        dislikeyn: String
    ): Response<MovieLikeAndDisLikeResponse> {
        return withContext(Dispatchers.IO) {
            movieService.requestMovieIncreaseLikeDisLikeForCoroutine(
                HashMap<String,String>().apply {
                    put("id",id.toString())
                    put("dislikeyn",dislikeyn)
                }
            )
        }
    }
}