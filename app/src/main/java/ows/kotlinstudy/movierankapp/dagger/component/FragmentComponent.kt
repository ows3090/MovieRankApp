package ows.kotlinstudy.movierankapp.dagger.component

import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import ows.kotlinstudy.movierankapp.adapter.FragmentScope
import ows.kotlinstudy.movierankapp.dagger.module.FragmentModule
import ows.kotlinstudy.movierankapp.dagger.module.ViewModelFactoryModule
import ows.kotlinstudy.movierankapp.dagger.module.ViewModelModule
import ows.kotlinstudy.movierankapp.view.MovieMainFragment

@Subcomponent(modules = [FragmentModule::class, ViewModelModule::class, ViewModelFactoryModule::class])
@FragmentScope
interface FragmentComponent {
    fun inject(movieMainFragment: MovieMainFragment)

    @Subcomponent.Builder
    interface Builder{
        fun setModule(fragmentModule: FragmentModule) : Builder
        @BindsInstance fun setFragment(movieMainFragment: MovieMainFragment) : Builder
        fun build() : FragmentComponent
    }
}