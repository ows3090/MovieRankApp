package ows.kotlinstudy.movierankapp.dagger.component

import dagger.BindsInstance
import dagger.Subcomponent
import ows.kotlinstudy.movierankapp.adapter.ActivityScope
import ows.kotlinstudy.movierankapp.dagger.module.ActivityModule
import ows.kotlinstudy.movierankapp.dagger.module.ViewModelFactoryModule
import ows.kotlinstudy.movierankapp.dagger.module.ViewModelModule
import ows.kotlinstudy.movierankapp.view.MainActivity
import ows.kotlinstudy.movierankapp.view.WritingActivity

@Subcomponent(modules = [ActivityModule::class,ViewModelModule::class, ViewModelFactoryModule::class])
@ActivityScope
interface ActivityComponent {
    fun inject(writingActivity: WritingActivity)
    fun inject(mainActivity: MainActivity)
    fun getFragmentBuilder() : FragmentComponent.Builder

    @Subcomponent.Builder
    interface Builder{
        fun setModule(activityModule: ActivityModule) : Builder
        fun build() : ActivityComponent
    }
}