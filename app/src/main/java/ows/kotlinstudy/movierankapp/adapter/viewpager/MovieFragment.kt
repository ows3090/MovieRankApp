package ows.kotlinstudy.movierankapp.adapter.viewpager

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
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
        binding.movieNameTextView.text = "${position + 1}. ${simpleMovie.title}"
        binding.movieInfoTextView.text =
            "예매율 ${simpleMovie.reservationRate}% | ${simpleMovie.grade}세 관람가 | 개봉일 : ${simpleMovie.date}"
        //loadProfileImage()
        Glide.with(binding.movieImageView.rootView)
            .load(simpleMovie.image)
            .into(binding.movieImageView)
    }

    private fun loadProfileImage() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            binding.progressBar.isVisible = true
            try {
                var inputstream = URL(simpleMovie.image).openConnection().getInputStream()
                val options = BitmapFactory.Options()
                options.inJustDecodeBounds = true
                BitmapFactory.decodeStream(inputstream, null, options)

                options.inSampleSize = calculateInSampleSize(options, binding.movieImageView.layoutParams.height, binding.movieImageView.layoutParams.width)
                options.inJustDecodeBounds = false

                inputstream.close()
                inputstream = URL(simpleMovie.image).openConnection().getInputStream()
                val bitmap = BitmapFactory.decodeStream(inputstream, null, options)
                if(bitmap == null){
                    Log.d("msg","bitmap null")
                }
                binding.movieImageView.setImageBitmap(bitmap)
                binding.progressBar.isVisible = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun decodeShrinkImageView(inputStream: InputStream, reqHeight: Int, reqWidth: Int ): Bitmap? {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, this)

            inSampleSize = calculateInSampleSize(this, reqHeight, reqWidth)

            inJustDecodeBounds = false

            BitmapFactory.decodeStream(inputStream, null, this)
        }
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqHeight: Int,
        reqWidth: Int
    ): Int {
        val height = options.outHeight
        val width = options.outWidth
        var samplsSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            while (halfHeight / samplsSize >= reqHeight && halfWidth / samplsSize >= reqWidth) {
                samplsSize*=2
            }
        }
        return samplsSize
    }


}

