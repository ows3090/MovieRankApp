package ows.kotlinstudy.movierankapp.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ows.kotlinstudy.movierankapp.response.MovieCommentResponse
import ows.kotlinstudy.movierankapp.response.MovieDetailResponse
import ows.kotlinstudy.movierankapp.response.MovieListResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
   private val movieService: MovieService
) {
   suspend fun fecthSimpleMovieList(type: Int) : Response<MovieListResponse> {
      return withContext(Dispatchers.IO){
         movieService.requestMovieListForCoroutine(type)
      }
   }

   suspend fun fetchMovieDetail(id : Int) : Response<MovieDetailResponse>{
      return withContext(Dispatchers.IO){
         movieService.requestMovieDetailForCoroutine(id)
      }
   }

   suspend fun fecthCommentList(id : Int) : Response<MovieCommentResponse>{
      return withContext(Dispatchers.IO){
         movieService.requestMovieCommentListForCoroutine(id)
      }
   }
}