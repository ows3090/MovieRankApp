package ows.kotlinstudy.movierankapp.dagger.component

import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import ows.kotlinstudy.movierankapp.adapter.FragmentScope
import ows.kotlinstudy.movierankapp.dagger.module.FragmentModule
import ows.kotlinstudy.movierankapp.dagger.module.ViewModelFactoryModule
import ows.kotlinstudy.movierankapp.dagger.module.ViewModelModule
import ows.kotlinstudy.movierankapp.view.MovieDetailFragment
import ows.kotlinstudy.movierankapp.view.MovieMainFragment

@Subcomponent(modules = [FragmentModule::class])
@FragmentScope
interface FragmentComponent {
    fun inject(movieMainFragment : MovieMainFragment)
    fun inject(movieDetailFragment : MovieDetailFragment)

    @Subcomponent.Builder
    interface Builder{
        fun setModule(fragmentModule: FragmentModule) : Builder
        fun build() : FragmentComponent
    }
}