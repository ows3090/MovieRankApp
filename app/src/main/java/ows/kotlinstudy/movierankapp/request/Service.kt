package ows.kotlinstudy.movierankapp.request

import ows.kotlinstudy.movierankapp.data.MovieListResponse
import ows.kotlinstudy.movierankapp.data.SimpleMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("movie/readMovieList")
    fun reqeustMovieList(@Query("type") type : Int) : Call<MovieListResponse>

    @GET("movie/readMovieList")
    suspend fun requestMovieListForCorotine(@Query("type") type: Int) : SimpleMovie


}