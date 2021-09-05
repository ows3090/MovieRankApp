package ows.kotlinstudy.movierankapp.dagger.module

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ows.kotlinstudy.movierankapp.viewmodel.ViewModelFactory

@Module
class ViewModelFactoryModule {
    @Provides
    fun getViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory{
        return viewModelFactory
    }
}