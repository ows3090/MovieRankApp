package ows.kotlinstudy.movierankapp.dagger.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ows.kotlinstudy.movierankapp.adapter.ViewModelKey
import ows.kotlinstudy.movierankapp.viewmodel.MovieDetailViewModel
import ows.kotlinstudy.movierankapp.viewmodel.MovieMainViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieMainViewModel::class)
    abstract fun bindMovieMainViewModel(movieMainViewModel: MovieMainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel) : ViewModel
}