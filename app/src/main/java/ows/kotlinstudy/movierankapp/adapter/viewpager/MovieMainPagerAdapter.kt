package ows.kotlinstudy.movierankapp.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ows.kotlinstudy.movierankapp.response.SimpleMovie

class MovieMainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val simpleMovies: ArrayList<Pair<SimpleMovie, Int>> = arrayListOf()

    fun addItems(items: List<SimpleMovie>) {
        simpleMovies.clear()
        for(i in items.indices){
            simpleMovies.add(Pair(items[i], items[i].id))
        }
    }

    override fun getItemCount(): Int = simpleMovies.size

    override fun createFragment(position: Int) = MovieFragment(simpleMovies.get(position).first, position)

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return simpleMovies.get(itemId.toInt()).first.id == simpleMovies.get(itemId.toInt()).second
    }
}
