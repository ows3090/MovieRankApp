package ows.kotlinstudy.movierankapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import kotlinx.coroutines.*
import ows.kotlinstudy.movierankapp.NetworkManager
import ows.kotlinstudy.movierankapp.NetworkStatus
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.adapter.viewpager.MovieMainPagerAdapter
import ows.kotlinstudy.movierankapp.dagger.module.FragmentModule
import ows.kotlinstudy.movierankapp.database.MovieDatabase
import ows.kotlinstudy.movierankapp.databinding.FragmentMoviemainBinding
import ows.kotlinstudy.movierankapp.viewmodel.MovieMainViewModel
import ows.kotlinstudy.movierankapp.viewmodel.ViewModelFactory
import javax.inject.Inject

class MovieMainFragment : Fragment() {

    private var binding: FragmentMoviemainBinding? = null
    private lateinit var movieMainPagerAdapter: MovieMainPagerAdapter
    private lateinit var movieMainViewModel: MovieMainViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentMoviemainBinding: FragmentMoviemainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_moviemain, container, false)
        binding = fragmentMoviemainBinding

        activity?.let {
            (it as MainActivity).getAcitivtyComponent().getFragmentBuilder()
                .setFragment(this)
                .setModule(FragmentModule())
                .build()
                .inject(this)
        }

        movieMainViewModel = ViewModelProvider(this, viewModelFactory).get(MovieMainViewModel::class.java)

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
            movieMainPagerAdapter.addItems(it)
        })
    }

    private fun loadMovieList() {
        movieMainViewModel.requestSimpleMovieList()
    }

}