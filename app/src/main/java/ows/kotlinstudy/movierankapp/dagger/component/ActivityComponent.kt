package ows.kotlinstudy.movierankapp.dagger.component

import dagger.BindsInstance
import dagger.Subcomponent
import ows.kotlinstudy.movierankapp.adapter.ActivityScope
import ows.kotlinstudy.movierankapp.dagger.module.ActivityModule
import ows.kotlinstudy.movierankapp.view.MainActivity

@Subcomponent(modules = [ActivityModule::class])
@ActivityScope
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun getFragmentBuilder() : FragmentComponent.Builder

    @Subcomponent.Builder
    interface Builder{
        fun setModule(activityModule: ActivityModule) : Builder
        @BindsInstance fun setActivity(mainActivity: MainActivity) : Builder
        fun build() : ActivityComponent
    }
}