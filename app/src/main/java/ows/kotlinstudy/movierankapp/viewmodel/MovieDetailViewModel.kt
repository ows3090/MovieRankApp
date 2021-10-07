package ows.kotlinstudy.movierankapp.viewmodel

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.adapter.recyclerview.CommentAdapter
import ows.kotlinstudy.movierankapp.adapter.recyclerview.GalleryAdapter
import ows.kotlinstudy.movierankapp.repository.MovieRepository
import ows.kotlinstudy.movierankapp.response.Comment
import ows.kotlinstudy.movierankapp.response.Movie
import ows.kotlinstudy.movierankapp.response.MovieCommentResponse
import java.text.DecimalFormat
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {

    private val movieMutableLiveData = MutableLiveData<Movie>()
    val movieLiveData: LiveData<Movie>
        get() = movieMutableLiveData

    private var commentListMutableLiveData = MutableLiveData<MovieCommentResponse>()
    val commentListLiveData: LiveData<MovieCommentResponse>
        get() = commentListMutableLiveData

    private val loadingMutableLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean>
        get() = loadingMutableLiveData

    fun requestMovieDetail(id: Int) {
        viewModelScope.launch {
            loadingMutableLiveData.value = true
            val response = repository.requestMovieDetail(id)

            if (response.code == 1) {
                response.data?.let {
                    it.result?.let { movies ->
                        movieMutableLiveData.value = movies.first()
                    }
                }
            }
            loadingMutableLiveData.value = false
        }
    }

    fun requestCommentList(id: Int) {
        viewModelScope.launch {
            val response = repository.requestCommentList(id)

            if (response.code == 1) {
                response.data?.let {
                    commentListMutableLiveData.value = it
                }
            }
        }
    }

    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadMovieImage(view : ImageView, url : String?){
            Glide.with(view.context)
                .load(url)
                .into(view)
        }

        @JvmStatic
        @BindingAdapter("grade")
        fun loadAgeImage(view: ImageView, grade : Int){
            when (grade) {
                12 -> Glide.with(view.context)
                    .load(R.drawable.ic_12)
                    .into(view)

                15 -> Glide.with(view.context)
                    .load(R.drawable.ic_15)
                    .into(view)

                19 -> Glide.with(view.context)
                    .load(R.drawable.ic_19)
                    .into(view)

                else -> Glide.with(view.context)
                    .load(R.drawable.ic_all)
                    .into(view)
            }
        }

        @JvmStatic
        @BindingAdapter("decimalformat")
        fun setDecimalformatText(view: TextView, audience : Int){
            view.text = "${DecimalFormat("#,###").format(audience)}명"
        }

        @JvmStatic
        @BindingAdapter("gallerylist")
        fun setGalleryRecyclerView(recylcerView: RecyclerView, items : String?){
            if(recylcerView.adapter == null){
                recylcerView.also {
                    it.layoutManager = LinearLayoutManager(recylcerView.context, LinearLayoutManager.HORIZONTAL, false)
                    it.adapter = GalleryAdapter()
                }
            }
            items?.let {
                val list = it.split(",") as ArrayList<String>
                with(recylcerView.adapter as GalleryAdapter){
                    clearItems()
                    addMovieItems(list)
                    notifyDataSetChanged()
                }
            }
        }

        @JvmStatic
        @BindingAdapter("commentList")
        fun setCommentListView(recyclerView: RecyclerView, items : List<Comment>?){
            if(recyclerView.adapter == null){
                recyclerView.also {
                    it.layoutManager = LinearLayoutManager(recyclerView.context)
                    it.adapter = CommentAdapter()
                }
            }

            items?.let{
                val list = ArrayList<Comment>(it.subList(0,2))
                (recyclerView.adapter as CommentAdapter).run{
                    addItems(list)
                    notifyDataSetChanged()
                }
            }
        }
    }
}