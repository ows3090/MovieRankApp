package ows.kotlinstudy.movierankapp.adapter.viewpager

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.databinding.FragmentMovieBinding
import ows.kotlinstudy.movierankapp.response.SimpleMovie
import java.io.IOException
import java.io.InputStream
import java.lang.NullPointerException
import java.lang.RuntimeException
import java.net.MalformedURLException
import java.net.URI
import java.net.URL

class MovieFragment(val simpleMovie: SimpleMovie, val position: Int) : Fragment() {

    private lateinit var binding: FragmentMovieBinding

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

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        Log.d("msg","initViews")
        binding.movieNameTextView.text = "${position + 1}. ${simpleMovie.title}"
        binding.movieInfoTextView.text =
            "예매율 ${simpleMovie.reservationRate}% | ${simpleMovie.grade}세 관람가 | 개봉일 : ${simpleMovie.date}"
        loadProfileImage()
    }

    private fun loadProfileImage() = GlobalScope.launch(Dispatchers.IO){
        try {
            val inputstream = URL(simpleMovie.image).openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputstream)
            binding.movieImageView.setImageBitmap(bitmap)
        } catch (e : Exception){
            e.printStackTrace()
        }
    }
}

