package ows.kotlinstudy.movierankapp.dagger

import dagger.Module
import dagger.Provides
import ows.kotlinstudy.movierankapp.repository.MovieRepository

@Module
object RepositoryModule {

    @Provides
    fun provideMovieRepository() : MovieRepository{
        return MovieRepository()
    }
}