package ows.kotlinstudy.movierankapp.dagger.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ows.kotlinstudy.movierankapp.adapter.ViewModelKey
import ows.kotlinstudy.movierankapp.viewmodel.MovieDetailViewModel
import ows.kotlinstudy.movierankapp.viewmodel.MovieMainViewModel
import ows.kotlinstudy.movierankapp.viewmodel.WritingViewModel

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

    @Binds
    @IntoMap
    @ViewModelKey(WritingViewModel::class)
    abstract fun bindWritingViewModel(writingViewModel: WritingViewModel) : ViewModel
}