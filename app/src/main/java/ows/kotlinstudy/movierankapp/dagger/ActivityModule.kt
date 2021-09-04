package ows.kotlinstudy.movierankapp.dagger

import dagger.Module
import dagger.Provides
import ows.kotlinstudy.movierankapp.adapter.ActivityScope
import ows.kotlinstudy.movierankapp.view.MainActivity
import javax.inject.Singleton


@Module
class ActivityModule {
    @Provides
    @ActivityScope
    fun provideActivityName(): String{
        return MainActivity::class.simpleName!!
    }
}