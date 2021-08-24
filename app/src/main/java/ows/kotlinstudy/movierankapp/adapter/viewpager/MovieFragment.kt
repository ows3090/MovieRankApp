package ows.kotlinstudy.movierankapp.adapter.viewpager

import android.icu.util.Calendar
import android.icu.util.LocaleData
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.data.SimpleMovieModel
import ows.kotlinstudy.movierankapp.databinding.FragmentMovieBinding
import java.time.LocalDate
import java.time.LocalDateTime

class MovieFragment(val simpleMovieModel: SimpleMovieModel) : Fragment() {

    private lateinit var binding : FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        Glide.with(binding.root).load(simpleMovieModel.image).into(binding.movieImageView)
        binding.movieNameTextView.text = simpleMovieModel.title
        binding.movieInfoTextView.text = "예매율 ${simpleMovieModel.reservationRate}% | ${simpleMovieModel.grade}세 관람가 | 개봉일 : ${simpleMovieModel.date}"
    }

}