package ows.kotlinstudy.movierankapp.adapter.viewpager

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ows.kotlinstudy.movierankapp.response.SimpleMovie

class MovieMainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val simpleMovies : ArrayList<SimpleMovie> = arrayListOf()
    private val hashCodes : ArrayList<Long> = arrayListOf()

    fun addItems(items : List<SimpleMovie>){
        simpleMovies.clear()
        simpleMovies.addAll(items)
        items.forEach { hashCodes.add(it.hashCode().toLong()) }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = simpleMovies.size

    override fun createFragment(position: Int) = MovieFragment(simpleMovies.get(position),position)

    override fun getItemId(position: Int): Long {
        return hashCodes.get(position)
    }

    override fun containsItem(itemId: Long): Boolean {
        return hashCodes.contains(itemId)
    }
}
