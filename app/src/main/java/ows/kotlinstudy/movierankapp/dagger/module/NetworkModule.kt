package ows.kotlinstudy.movierankapp.dagger.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ows.kotlinstudy.movierankapp.BuildConfig
import ows.kotlinstudy.movierankapp.MovieApplication
import ows.kotlinstudy.movierankapp.NetworkStatus
import ows.kotlinstudy.movierankapp.dagger.component.ActivityComponent
import ows.kotlinstudy.movierankapp.repository.MovieService
import ows.kotlinstudy.movierankapp.repository.Url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Reusable
    fun provideNetworkStatus(movieApplication: MovieApplication) : NetworkStatus {
        val manager = movieApplication.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            manager.activeNetwork?.let {
                val networkCapabilities = manager.getNetworkCapabilities(it) ?: return@let
                when{
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return NetworkStatus.WIFI
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return NetworkStatus.CELLULAR
                    else -> NetworkStatus.NOT_CONNECTED
                }
            }
        }else{
            manager.activeNetworkInfo?.let {
                when(it.type){
                    ConnectivityManager.TYPE_WIFI -> return NetworkStatus.WIFI
                    ConnectivityManager.TYPE_MOBILE -> return NetworkStatus.CELLULAR
                    else -> return NetworkStatus.NOT_CONNECTED
                }
            }
        }
        return NetworkStatus.NOT_CONNECTED
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource() : MovieService {
        val interceptor = HttpLoggingInterceptor()
        if(BuildConfig.DEBUG){
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(5,TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Url.MOVIE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MovieService::class.java)
    }
}