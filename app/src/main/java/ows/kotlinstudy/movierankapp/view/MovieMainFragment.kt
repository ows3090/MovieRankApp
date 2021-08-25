package ows.kotlinstudy.movierankapp.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import ows.kotlinstudy.movierankapp.NetworkManager
import ows.kotlinstudy.movierankapp.NetworkStatus
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.adapter.viewpager.MovieMainPagerAdapter
import ows.kotlinstudy.movierankapp.database.MovieDatabase
import ows.kotlinstudy.movierankapp.databinding.FragmentMoviemainBinding
import ows.kotlinstudy.movierankapp.viewmodel.MovieMainViewModel

class MovieMainFragment : Fragment() {

    private lateinit var binding: FragmentMoviemainBinding
    private lateinit var movieMainPagerAdapter: MovieMainPagerAdapter
    private val movieMainViewModel by lazy { ViewModelProvider(this).get(MovieMainViewModel::class.java) }

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
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_moviemain, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
        loadMovieList()
    }

    private fun initViews() {
        movieMainPagerAdapter = MovieMainPagerAdapter(this)
        binding.viewPager.adapter = movieMainPagerAdapter
    }

    private fun bindViews() {
        movieMainViewModel.getLoading().observe(viewLifecycleOwner, {
            binding.progresBar.isVisible = it
        })

        movieMainViewModel.getSimpleMovies().observe(viewLifecycleOwner, {
            Thread {
                db.movieDao().insertAllMovie(it)
            }.start()

            movieMainPagerAdapter.addItems(it)
            movieMainPagerAdapter.notifyDataSetChanged()
        })
    }

    private fun loadMovieList() {
        if (NetworkManager.getNetworkConnectedStatus(requireContext()) == NetworkStatus.NOT_CONNECTED) {
            Log.d("msg","not connected")
            Thread {
                Log.d("msg",""+db.movieDao().getAll().size)
                movieMainPagerAdapter.addItems(db.movieDao().getAll())
                movieMainPagerAdapter.notifyDataSetChanged()
            }.start()
        } else {
            Log.d("msg","connected")
            movieMainViewModel.requestSimpleMovieList(1)
        }
    }


}