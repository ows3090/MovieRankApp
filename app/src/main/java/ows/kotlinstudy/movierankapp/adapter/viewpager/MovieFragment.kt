package ows.kotlinstudy.movierankapp.adapter.viewpager

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
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
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.*
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.databinding.FragmentMovieBinding
import ows.kotlinstudy.movierankapp.response.SimpleMovie
import ows.kotlinstudy.movierankapp.view.MainActivity
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.lang.NullPointerException
import java.lang.RuntimeException
import java.net.MalformedURLException
import java.net.URI
import java.net.URL
import kotlin.math.max

class MovieFragment(var simpleMovie: SimpleMovie, val position: Int) : Fragment() {

    private var binding: FragmentMovieBinding? = null
    private var bitmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentMovieBinding : FragmentMovieBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        binding = fragmentMovieBinding
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViews()
    }

    override fun onDestroyView() {
        bitmap = null
        binding = null
        super.onDestroyView()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        binding?.let { binding ->
            binding.movieNameTextView.text = "${position + 1}. ${simpleMovie.title}"
            binding.movieInfoTextView.text =
                "예매율 ${simpleMovie.reservationRate}% | ${simpleMovie.grade}세 관람가 | 개봉일 : ${simpleMovie.date.replace('-','.')}"

            Glide.with(binding.movieImageView.rootView)
                .load(simpleMovie.image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.isVisible = true
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.isVisible = false
                        return false
                    }
                })
                .into(binding.movieImageView)
        }
        //loadProfileImage()
    }

    private fun bindViews() {
        binding?.movieDetailButton?.setOnClickListener {
            (activity as? MainActivity)?.showMovieDetail(simpleMovie.id)
        }
    }

    fun notifyUpdate(){
        initViews()
    }


//    private fun loadProfileImage() {
//        if(memoryCache.get(simpleMovie.image) != null){
//            bitmap = memoryCache.get(simpleMovie.image)
//            binding.movieImageView.setImageBitmap(bitmap)
//        }else{
//            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
//                try {
//                    var inputstream = URL(simpleMovie.image).openConnection().getInputStream()
//                    var bufferedInputStream = BufferedInputStream(inputstream)
//                    val options = BitmapFactory.Options()
//
//                    bufferedInputStream.mark(bufferedInputStream.available())
//                    options.inJustDecodeBounds = true
//                    BitmapFactory.decodeStream(bufferedInputStream, null, options)
//
//                    bufferedInputStream.reset()
//                    options.inSampleSize = calculateInSampleSize(
//                        options,
//                        binding.movieImageView.layoutParams.width,
//                        binding.movieImageView.layoutParams.height
//                    )
//                    options.inJustDecodeBounds = false
//
//                    bitmap = BitmapFactory.decodeStream(bufferedInputStream, null, options)
//                    memoryCache.put(simpleMovie.image, bitmap)
//                    binding.movieImageView.setImageBitmap(bitmap)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        val height = options.outHeight
        val width = options.outWidth
        var samplsSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2

            while (halfHeight / samplsSize >= reqHeight && halfWidth / samplsSize >= reqWidth) {
                samplsSize *= 2
            }
        }
        return samplsSize
    }

    companion object {
        val maxMemory = Runtime.getRuntime().maxMemory().toInt()
        val cacheSize = maxMemory / 8
        val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String?, bitmap: Bitmap): Int {
                return bitmap.byteCount / 1024
            }
        }
    }


}

