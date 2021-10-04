package ows.kotlinstudy.movierankapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ows.kotlinstudy.movierankapp.Constants.MOVIEID
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.adapter.recyclerview.GalleryAdapter
import ows.kotlinstudy.movierankapp.dagger.module.FragmentModule
import ows.kotlinstudy.movierankapp.databinding.FragmentMoviedetailBinding
import ows.kotlinstudy.movierankapp.repository.Url.GALLERY_DEFAULT_URL
import ows.kotlinstudy.movierankapp.response.Comment
import ows.kotlinstudy.movierankapp.response.Movie
import ows.kotlinstudy.movierankapp.viewmodel.MovieDetailViewModel
import ows.kotlinstudy.movierankapp.viewmodel.ViewModelFactory
import java.text.DecimalFormat
import javax.inject.Inject

class MovieDetailFragment : Fragment() {

    private var binding: FragmentMoviedetailBinding? = null
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val movieId : Int by lazy { arguments?.getInt(MOVIEID, 0) ?: 0}
    private val galleryAdapter by lazy { GalleryAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentMovieDetailBinding: FragmentMoviedetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_moviedetail, container, false)
        binding = fragmentMovieDetailBinding
        initViewModel()

        return fragmentMovieDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViews()
        loadMovieDetailInfo()
        loadCommentList()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initViewModel() {
        activity?.let {
            (it as MainActivity).getAcitivtyComponent().getFragmentBuilder()
                .setModule(FragmentModule())
                .build()
                .inject(this)
        }
        movieDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
    }

    private fun initViews(){
        binding?.let {
            it.movieGalleryRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = galleryAdapter
            }
        }
    }

    private fun bindViews() {
        movieDetailViewModel.movieLiveData.observe(viewLifecycleOwner){
            setMovieDetailInfo(it)
            setGalleryList(it)
        }

        movieDetailViewModel.commentListLiveData.observe(viewLifecycleOwner){
            setCommentList(it)
        }
    }

    private fun loadMovieDetailInfo(){
        movieDetailViewModel.requestMovieDetail(movieId)
    }

    private fun loadCommentList(){
        movieDetailViewModel.requestCommentList(movieId)
    }

    private fun setMovieDetailInfo(movie : Movie) = binding?.let {
        Glide.with(it.movieImageView.context)
            .load(movie.image)
            .into(it.movieImageView)
        it.titleTextView.text = movie.title
        setMovieGradeInfo(movie.grade)

        it.movieInfoTextView.text = "${movie.date?.replace('-','.')} 개봉"
        it.movieOtherInfoTextView.text = "${movie.genre} / ${movie.duration}분"
        it.likeCountTextView.text = "${movie.like}"
        it.dislikeCountTextView.text = "${movie.dislike}"
        it.movieReservationResultTextView.text = "${movie.reservationGrade}위 ${movie.reservationRate}%"
        it.movieRatingBar.rating = movie.userRating.toFloat()
        it.movieRatingResultTextView.text = "${movie.userRating}"
        it.movieAudienceResultTextView.text = "${DecimalFormat("#,###").format(movie.audience)}명"
        it.movieStoryDetailTextView.text = movie.synopsis
        it.movieDirectorResultTextView.text = movie.director
        it.moviePerformerResultTextView.text = movie.actor
    }

    private fun setMovieGradeInfo(grade : Int){
        binding?.let {
            when(grade){
                12 -> Glide.with(it.ageImageView.context)
                    .load(R.drawable.ic_12)
                    .into(it.ageImageView)

                15 -> Glide.with(it.ageImageView.context)
                    .load(R.drawable.ic_15)
                    .into(it.ageImageView)

                19 -> Glide.with(it.ageImageView.context)
                    .load(R.drawable.ic_19)
                    .into(it.ageImageView)

                else -> Glide.with(it.ageImageView.context)
                    .load(R.drawable.ic_all)
                    .into(it.ageImageView)
            }
        }
    }

    private fun setGalleryList(movie : Movie){
        galleryAdapter.clearItems()

        movie.videos?.let {
            galleryAdapter.addMovieItems(it.split(",").map {
                GALLERY_DEFAULT_URL + it.substring(17) + "/0.jpg"
            } as ArrayList<String>)
        }

        movie.photos?.let {
            galleryAdapter.addGalleryItems(it.split(",") as ArrayList<String>)
        }

        galleryAdapter.notifyDataSetChanged()
    }

    private fun setCommentList(comments : List<Comment>){

    }
}