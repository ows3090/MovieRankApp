package ows.kotlinstudy.movierankapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object NetworkManager {
   fun getNetworkConnectedStatus(context: Context): NetworkStatus{
       val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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
}