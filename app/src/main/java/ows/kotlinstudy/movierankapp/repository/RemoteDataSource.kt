package ows.kotlinstudy.movierankapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
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
                MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("id",id.toString())
                    .addFormDataPart("likeyn",likeyn)
                    .build()
            )
        }
    }

    suspend fun fetchMovieDisLike(
        id: Int,
        dislikeyn: String
    ): Response<MovieLikeAndDisLikeResponse> {
        return withContext(Dispatchers.IO) {
            movieService.requestMovieIncreaseLikeDisLikeForCoroutine(
                MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("id",id.toString())
                    .addFormDataPart("dislikeyn",dislikeyn)
                    .build()
            )
        }
    }
}