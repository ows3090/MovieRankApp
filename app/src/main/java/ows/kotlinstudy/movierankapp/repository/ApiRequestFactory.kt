package ows.kotlinstudy.movierankapp.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ows.kotlinstudy.movierankapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiRequestFactory {
    val retrofit = Retrofit.Builder()
        .baseUrl(Url.MOVIE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(buildOkHttpClient())
        .build()
        .create(MovieService::class.java)

    private fun buildOkHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }
}