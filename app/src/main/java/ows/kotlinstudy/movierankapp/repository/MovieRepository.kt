package ows.kotlinstudy.movierankapp.repository

import ows.kotlinstudy.movierankapp.NetworkStatus
import ows.kotlinstudy.movierankapp.repository.response.MovieCommentResponse
import ows.kotlinstudy.movierankapp.repository.response.MovieDetailResponse
import ows.kotlinstudy.movierankapp.repository.response.MovieLikeAndDisLikeResponse
import ows.kotlinstudy.movierankapp.repository.response.MovieListResponse
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    @Inject
    lateinit var networkStatus: NetworkStatus

    suspend fun requestSimpleMovieList(type: Int): ResponseResult<MovieListResponse> {
        /**
         * fetch in LocalDataSource
         */
        if (networkStatus == NetworkStatus.NOT_CONNECTED) {
            return localDataSource.fecthSimpleMovieList(type)
        }

        /**
         * fetch in RemoteDataSource
         */
        val response = remoteDataSource.fecthSimpleMovieList(type)
        if (response.isSuccessful) {
            localDataSource.insertSimpleMovieList(response.body()?.result)
            return ResponseResult.Success(response.body(), 1)
        }
        return ResponseResult.Fail(response.body(), 2)
    }

    suspend fun requestMovieDetail(id: Int): ResponseResult<MovieDetailResponse> {
        /**
         * fetch in LocalDataSource
         */
        if (networkStatus == NetworkStatus.NOT_CONNECTED) {
            return localDataSource.fetchMovieDetail(id)
        }

        /**
         * fetch in RemoteDataSource
         */
        val response = remoteDataSource.fetchMovieDetail(id)
        if (response.isSuccessful) {
            localDataSource.insertMovieDetail(response.body()?.result?.first())
            return ResponseResult.Success(response.body(), 1)
        }
        return ResponseResult.Fail(response.body(), 2)
    }

    suspend fun requestCommentList(id: Int): ResponseResult<MovieCommentResponse> {
        /**
         * fetch in LocalDataSource
         */
        if (networkStatus == NetworkStatus.NOT_CONNECTED) {
            return localDataSource.fetchCommentList(id)
        }

        /**
         * fetch in RemoteDataSource
         */
        val response = remoteDataSource.fecthCommentList(id)
        if (response.isSuccessful) {
            localDataSource.insertCommentList(response.body()?.result)
            return ResponseResult.Success(response.body(), 1)
        }
        return ResponseResult.Fail(response.body(), 2)
    }

    suspend fun requestMovieLike(
        id: Int,
        likeyn: String
    ): ResponseResult<MovieLikeAndDisLikeResponse> {
        if (networkStatus == NetworkStatus.NOT_CONNECTED) {
            return ResponseResult.Fail(null, 2)
        }

        val response = remoteDataSource.fetchMovieLike(id, likeyn)
        if (response.isSuccessful) {
            return ResponseResult.Success(response.body(), 1)
        }
        return ResponseResult.Fail(response.body(), 2)
    }

    suspend fun requestMovieDisLike(
        id: Int,
        dislikeyn: String
    ): ResponseResult<MovieLikeAndDisLikeResponse> {
        if (networkStatus == NetworkStatus.NOT_CONNECTED) {
            return ResponseResult.Fail(null, 2)
        }

        val response = remoteDataSource.fetchMovieDisLike(id, dislikeyn)
        if (response.isSuccessful) {
            return ResponseResult.Success(response.body(), 1)
        }
        return ResponseResult.Fail(response.body(), 2)
    }
}