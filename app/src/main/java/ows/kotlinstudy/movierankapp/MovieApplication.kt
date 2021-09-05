package ows.kotlinstudy.movierankapp

import android.app.Application
import ows.kotlinstudy.movierankapp.dagger.component.AppComponent
import ows.kotlinstudy.movierankapp.dagger.module.DBModule
import ows.kotlinstudy.movierankapp.dagger.component.DaggerAppComponent
import ows.kotlinstudy.movierankapp.dagger.module.NetworkModule

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