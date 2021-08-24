package ows.kotlinstudy.movierankapp.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ows.kotlinstudy.movierankapp.data.SimpleMovieModel

class MovieMainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val simpleMovieModels = mutableListOf<SimpleMovieModel>()

    fun addItem(simpleMovieModel: SimpleMovieModel) = simpleMovieModels.add(simpleMovieModel)

    override fun getItemCount(): Int = simpleMovieModels.size

    override fun createFragment(position: Int) = MovieFragment(simpleMovieModels.get(position))
}