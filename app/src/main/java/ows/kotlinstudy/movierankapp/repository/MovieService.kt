package ows.kotlinstudy.movierankapp.repository

import ows.kotlinstudy.movierankapp.response.MovieCommentResponse
import ows.kotlinstudy.movierankapp.response.MovieDetailResponse
import ows.kotlinstudy.movierankapp.response.MovieListResponse
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
}