package ows.kotlinstudy.movierankapp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ows.kotlinstudy.movierankapp.Constants.COMMENTLIST
import ows.kotlinstudy.movierankapp.Constants.MOVIEID
import ows.kotlinstudy.movierankapp.Constants.MOVIEINFO
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.adapter.recyclerview.GalleryAdapter
import ows.kotlinstudy.movierankapp.dagger.module.FragmentModule
import ows.kotlinstudy.movierankapp.databinding.FragmentMoviedetailBinding
import ows.kotlinstudy.movierankapp.viewmodel.MovieDetailViewModel
import ows.kotlinstudy.movierankapp.viewmodel.ViewModelFactory
import javax.inject.Inject

class MovieDetailFragment : Fragment() {

    private var binding: FragmentMoviedetailBinding? = null
    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            movieDetailViewModel.requestCommentList(movieId)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val movieId: Int by lazy { arguments?.getInt(MOVIEID, 0) ?: 0 }
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
        binding?.apply {
            lifecycleOwner = this@MovieDetailFragment
            viewmodel = movieDetailViewModel
        }

        return fragmentMovieDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        bindViews()
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

    private fun initViews() {
        binding?.let {
            it.movieGalleryRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = galleryAdapter
            }
        }

        movieDetailViewModel.run {
            requestMovieDetail(movieId)
            requestCommentList(movieId)
        }
    }

    private fun bindViews() {
        binding?.let { binding ->
            movieDetailViewModel.loadingLiveData.observe(viewLifecycleOwner) { check ->
                binding.let {
                    it.progresBar.isVisible = check
                    it.movieStoryProgressBar.isVisible = check
                    it.titleTextView.isVisible = !check
                    it.movieInfoTextView.isVisible = !check
                    it.movieOtherInfoTextView.isVisible = !check
                    it.movieImageView.isVisible = !check
                    it.ageImageView.isVisible = !check
                    it.likeButton.isVisible = !check
                    it.dislikeButton.isVisible = !check
                    it.likeCountTextView.isVisible = !check
                    it.dislikeCountTextView.isVisible = !check
                    it.movieStoryDetailTextView.isVisible = !check
                }
            }

            binding.viewButton.setOnClickListener {
                startActivity(
                    Intent(requireContext(), CommentActivity::class.java).run {
                        putExtra(MOVIEINFO, movieDetailViewModel.movieLiveData.value)
                        putExtra(COMMENTLIST, movieDetailViewModel.commentListLiveData.value)
                    }
                )
            }

            binding.likeButton.setOnClickListener {
                movieDetailViewModel.likeStateLiveData.value?.let {
                    if (it) movieDetailViewModel.requestMovieLike(movieId, "N")
                    else {
                        if (movieDetailViewModel.dislikeStateLiveData.value!!) {
                            movieDetailViewModel.requestMovieDisLike(movieId, "N")
                        }
                        movieDetailViewModel.requestMovieLike(movieId, "Y")
                    }
                }
            }

            binding.dislikeButton.setOnClickListener {
                movieDetailViewModel.dislikeStateLiveData.value?.let {
                    if (it) movieDetailViewModel.requestMovieDisLike(movieId, "N")
                    else {
                        if (movieDetailViewModel.likeStateLiveData.value!!) {
                            movieDetailViewModel.requestMovieLike(movieId, "N")
                        }
                        movieDetailViewModel.requestMovieDisLike(movieId, "Y")
                    }
                }
            }

            movieDetailViewModel.likeStateLiveData.observe(viewLifecycleOwner) {
                if (it) binding.likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected)
                else binding.likeButton.setBackgroundResource(R.drawable.ic_thumb_up)
            }

            movieDetailViewModel.dislikeStateLiveData.observe(viewLifecycleOwner) {
                if (it) binding.dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected)
                else binding.dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down)
            }

            binding.writeButton.setOnClickListener {
                resultLauncher.launch(
                    Intent(
                        requireContext(), WritingActivity::class.java
                    ).apply {
                        putExtra(MOVIEINFO, movieDetailViewModel.movieLiveData.value)
                    }
                )
            }
        }
    }
}