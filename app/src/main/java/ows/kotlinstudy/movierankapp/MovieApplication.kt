package ows.kotlinstudy.movierankapp

import android.app.Application
import ows.kotlinstudy.movierankapp.dagger.AppComponent
import ows.kotlinstudy.movierankapp.dagger.DBModule
import ows.kotlinstudy.movierankapp.dagger.DaggerAppComponent
import ows.kotlinstudy.movierankapp.dagger.NetworkModule

class MovieApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(
            this,
            DBModule(),
            NetworkModule()
        )
    }

    fun getAppComponent() = appComponent
}