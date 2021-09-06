package ows.kotlinstudy.movierankapp.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ows.kotlinstudy.movierankapp.response.SimpleMovie

class MovieMainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val simpleMovies : ArrayList<SimpleMovie> = arrayListOf()

    fun addItems(items : List<SimpleMovie>){
        simpleMovies.clear()
        simpleMovies.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = simpleMovies.size

    override fun createFragment(position: Int) = MovieFragment(simpleMovies.get(position),position)
}
