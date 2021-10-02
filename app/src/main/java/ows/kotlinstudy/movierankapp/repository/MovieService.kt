package ows.kotlinstudy.movierankapp.repository

import ows.kotlinstudy.movierankapp.response.MovieDetailResponse
import ows.kotlinstudy.movierankapp.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/readMovieList")
    suspend fun requestMovieListForCorotine(@Query("type") type: Int) : Response<MovieListResponse>

    @GET("readMovie")
    suspend fun requestMovieDetailForCorotine(@Query("id") id : Int) : Response<MovieDetailResponse>
}