package ows.kotlinstudy.movierankapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.ListView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.*
import ows.kotlinstudy.movierankapp.NetworkManager
import ows.kotlinstudy.movierankapp.NetworkStatus
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.adapter.viewpager.MovieMainPagerAdapter
import ows.kotlinstudy.movierankapp.database.MovieDatabase
import ows.kotlinstudy.movierankapp.databinding.FragmentMoviemainBinding
import ows.kotlinstudy.movierankapp.viewmodel.MovieMainViewModel
import kotlin.coroutines.CoroutineContext

class MovieMainFragment : Fragment() {

    private var binding: FragmentMoviemainBinding? = null
    private lateinit var movieMainPagerAdapter: MovieMainPagerAdapter
    private val movieMainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(
            MovieMainViewModel::class.java
        )
    }

    private val db by lazy {
        Room.databaseBuilder(
            requireContext(),
            MovieDatabase::class.java,
            "Movie-Database"
        ).build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentMoviemainBinding: FragmentMoviemainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_moviemain, container, false)
        binding = fragmentMoviemainBinding
        return fragmentMoviemainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
        loadMovieList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initViews() {
        movieMainPagerAdapter = MovieMainPagerAdapter(this)
        binding?.let {
            it.viewPager.adapter = movieMainPagerAdapter
        }
    }

    private fun bindViews() {
        movieMainViewModel.getLoading().observe(viewLifecycleOwner, {
            binding?.progresBar?.isVisible = it
        })

        movieMainViewModel.getSimpleMovies().observe(viewLifecycleOwner, {
//            launch(Dispatchers.IO){
//                db.movieDao().insertAllMovie(it)
//            }
            movieMainPagerAdapter.addItems(it)
            movieMainPagerAdapter.notifyDataSetChanged()
        })
    }

    private fun loadMovieList() {
        if (NetworkManager.getNetworkConnectedStatus(requireContext()) == NetworkStatus.NOT_CONNECTED) {
            Thread {
                Log.d("msg", "" + db.movieDao().getAll().size)
                movieMainPagerAdapter.addItems(db.movieDao().getAll())
                movieMainPagerAdapter.notifyDataSetChanged()
            }.start()
        } else {
            movieMainViewModel.requestSimpleMovieList(1)
        }
    }


}