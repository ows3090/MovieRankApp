package ows.kotlinstudy.movierankapp.adapter.viewpager

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ows.kotlinstudy.movierankapp.response.SimpleMovie

class MovieMainPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val simpleMovies: ArrayList<SimpleMovie> = arrayListOf()
    private val fragmentList: ArrayList<MovieFragment> = arrayListOf()

    fun addItems(items: List<SimpleMovie>) {
        simpleMovies.clear()
        simpleMovies.addAll(items)
        notifyDataSetChanged()

        for(i in 0 until fragmentList.size){
            fragmentList.get(i).simpleMovie = simpleMovies.get(i)
            fragmentList.get(i).notifyUpdate()
        }
    }

    override fun getCount(): Int {
        return simpleMovies.size
    }

    override fun getItem(position: Int): Fragment {
        fragmentList.add(MovieFragment(simpleMovies.get(position), position))
        return fragmentList.get(position)
    }
}
