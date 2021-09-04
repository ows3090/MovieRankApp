package ows.kotlinstudy.movierankapp.dagger

import dagger.Module
import dagger.Provides
import ows.kotlinstudy.movierankapp.repository.MovieRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository() : MovieRepository{
        return MovieRepository()
    }
}