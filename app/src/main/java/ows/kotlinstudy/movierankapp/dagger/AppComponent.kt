package ows.kotlinstudy.movierankapp.dagger

import dagger.BindsInstance
import dagger.Component
import ows.kotlinstudy.movierankapp.MovieApplication
import javax.inject.Singleton

@Component(modules = [DBModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(movieApplication: MovieApplication)
    fun getActivityBuilder() : ActivityComponent.Builder

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance movieApplication: MovieApplication,
            dbModule: DBModule,
            networkModule: NetworkModule
        ): AppComponent
    }
}