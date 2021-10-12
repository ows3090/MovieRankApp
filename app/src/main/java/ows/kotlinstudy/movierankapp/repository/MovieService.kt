package ows.kotlinstudy.movierankapp.repository

import okhttp3.RequestBody
import ows.kotlinstudy.movierankapp.repository.response.MovieCommentResponse
import ows.kotlinstudy.movierankapp.repository.response.MovieDetailResponse
import ows.kotlinstudy.movierankapp.repository.response.MovieLikeAndDisLikeResponse
import ows.kotlinstudy.movierankapp.repository.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieService {

    @GET("readMovieList")
    suspend fun requestMovieListForCoroutine(@Query("type") type: Int) : Response<MovieListResponse>

    @GET("readMovie")
    suspend fun requestMovieDetailForCoroutine(@Query("id") id : Int) : Response<MovieDetailResponse>

    @GET("readCommentList")
    suspend fun requestMovieCommentListForCoroutine(@Query("id") id : Int) : Response<MovieCommentResponse>

    @POST("increaseLikeDisLike")
    suspend fun requestMovieIncreaseLikeDisLikeForCoroutine(@Body body : RequestBody) : Response<MovieLikeAndDisLikeResponse>

}