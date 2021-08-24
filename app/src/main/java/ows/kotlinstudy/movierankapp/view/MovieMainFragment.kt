package ows.kotlinstudy.movierankapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.adapter.viewpager.MovieMainPagerAdapter
import ows.kotlinstudy.movierankapp.data.SimpleMovieModel
import ows.kotlinstudy.movierankapp.databinding.ActivityMainBinding
import ows.kotlinstudy.movierankapp.databinding.FragmentMoviemainBinding

class MovieMainFragment : Fragment() {

    private lateinit var binding: FragmentMoviemainBinding
    private lateinit var movieMainPagerAdapter: MovieMainPagerAdapter

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

        val simpleMovieModel = SimpleMovieModel(
            id = 5,
            title = "범죄도시",
            titleEng = "The Outlaws",
            date = "2017-10-03",
            userRating = 3.7,
            audienceRating = 9.27,
            reviewerRating = 6.12,
            reservationRate = 2.37,
            reservationGrade = 5,
            grade = 19,
            thumb = "http://movie2.phinf.naver.net/20170915_299/1505458505658vbKcN_JPEG/movie_image.jpg?type=m99_141_2",
            image = "http://movie.phinf.naver.net/20170915_299/1505458505658vbKcN_JPEG/movie_image.jpg"
        )
        movieMainPagerAdapter.addItem(simpleMovieModel)
        movieMainPagerAdapter.notifyDataSetChanged()
    }

    private fun initViews() {
        movieMainPagerAdapter = MovieMainPagerAdapter(this)
        binding.viewPager.adapter = movieMainPagerAdapter
    }


}