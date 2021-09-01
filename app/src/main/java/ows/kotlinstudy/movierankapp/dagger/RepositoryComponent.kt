package ows.kotlinstudy.movierankapp.dagger

import dagger.Component
import dagger.Module
import ows.kotlinstudy.movierankapp.dagger.RepositoryModule
import ows.kotlinstudy.movierankapp.viewmodel.MovieMainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {
    fun inject(movieMainViewModel: MovieMainViewModel)
}