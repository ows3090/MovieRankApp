package ows.kotlinstudy.movierankapp.repo

import ows.kotlinstudy.movierankapp.request.ApiRequestFactory

class MovieRepository {
    fun requestMovieList(type: Int) = ApiRequestFactory.retrofit.reqeustMovieList(type)
}