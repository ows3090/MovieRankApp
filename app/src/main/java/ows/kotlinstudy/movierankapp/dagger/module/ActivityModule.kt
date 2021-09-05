package ows.kotlinstudy.movierankapp.dagger.module

import dagger.Module
import dagger.Provides
import ows.kotlinstudy.movierankapp.adapter.ActivityScope
import ows.kotlinstudy.movierankapp.dagger.component.FragmentComponent
import ows.kotlinstudy.movierankapp.view.MainActivity
import javax.inject.Singleton


@Module(subcomponents = [FragmentComponent::class])
class ActivityModule {
}