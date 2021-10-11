package ows.kotlinstudy.movierankapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore
import ows.kotlinstudy.movierankapp.repository.MovieService
import ows.kotlinstudy.movierankapp.repository.Url
import ows.kotlinstudy.movierankapp.repository.request.MovieLikeAndDisLikeRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    @Ignore
    fun requestCommentListTest() {
        runBlocking {
            val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val movieService = Retrofit.Builder()
                .baseUrl(Url.MOVIE_URL)
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)

            launch(Dispatchers.IO){
                val response = movieService.requestMovieCommentListForCoroutine(1)
            }
        }
    }

    @Test
    fun requestMovieLikeTest(){
        runBlocking {
            val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val movieService = Retrofit.Builder()
                .baseUrl(Url.MOVIE_URL)
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)

            launch(Dispatchers.IO){
                val response = movieService.requestMovieIncreaseLikeDisLikeForCoroutine(
                    HashMap<String,String>().apply {
                        put("id","1")
                        put("likeyn","N")
                    }
                )
            }
        }
    }
}