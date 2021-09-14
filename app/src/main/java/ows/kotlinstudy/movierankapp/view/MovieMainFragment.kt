package ows.kotlinstudy.movierankapp.view

import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.adapter.viewpager.MovieMainPagerAdapter
import ows.kotlinstudy.movierankapp.dagger.module.FragmentModule
import ows.kotlinstudy.movierankapp.databinding.FragmentMoviemainBinding
import ows.kotlinstudy.movierankapp.viewmodel.MovieMainViewModel
import ows.kotlinstudy.movierankapp.viewmodel.ViewModelFactory
import javax.inject.Inject

class MovieMainFragment : Fragment(), Animation.AnimationListener {

    private var binding: FragmentMoviemainBinding? = null
    private lateinit var movieMainPagerAdapter: MovieMainPagerAdapter
    private lateinit var movieMainViewModel: MovieMainViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private var isMenuOpen: Boolean = false
    private lateinit var menuTextView : TextView
    private lateinit var sortName : String
    private lateinit var translateTop : Animation
    private lateinit var translateBottom : Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentMoviemainBinding: FragmentMoviemainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_moviemain, container, false)
        setHasOptionsMenu(true)
        binding = fragmentMoviemainBinding
        initViewModel()

        return fragmentMoviemainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initMenuAnimtaion()
        bindViews()
        loadMovieList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_moviesort, menu)
        (menu.findItem(R.id.sortItem).actionView as LinearLayout).run {
            setOnClickListener {
                if(isMenuOpen){
                    binding?.menuLayout?.startAnimation(translateTop)
                }else{
                    binding?.menuLayout?.isVisible = isMenuOpen
                    binding?.menuLayout?.startAnimation(translateBottom)
                }
            }
            menuTextView = findViewById(R.id.menuTextView)
        }
    }

    private fun initViewModel() {
        activity?.let {
            (it as MainActivity).getAcitivtyComponent().getFragmentBuilder()
                .setModule(FragmentModule())
                .build()
                .inject(this)
        }

        movieMainViewModel =
            ViewModelProvider(this, viewModelFactory).get(MovieMainViewModel::class.java)
    }

    private fun initViews() {
        movieMainPagerAdapter = MovieMainPagerAdapter(this)
        binding?.let {
            it.viewPager.adapter = movieMainPagerAdapter
        }
    }

    private fun initMenuAnimtaion() {
        translateTop = AnimationUtils.loadAnimation(requireContext(), R.anim.translate_top)
        translateBottom = AnimationUtils.loadAnimation(requireContext(), R.anim.translate_bottom)
        translateTop.setAnimationListener(this)
        translateBottom.setAnimationListener(this)
    }

    private fun bindViews() {
        movieMainViewModel.getLoadingLiveData().observe(viewLifecycleOwner, {
            binding?.progresBar?.isVisible = it
        })

        movieMainViewModel.getSimpleMoviesLiveData().observe(viewLifecycleOwner, {
            movieMainPagerAdapter.addItems(it)
        })

        movieMainViewModel.getSortNameLiveData().observe(viewLifecycleOwner, {
            menuTextView.text = it
        })
    }

    private fun loadMovieList() {
        movieMainViewModel.requestSimpleMovieList()
    }

    override fun onAnimationStart(p0: Animation?) = Unit

    override fun onAnimationEnd(p0: Animation?) {
        if(isMenuOpen){
            isMenuOpen = false
            binding?.menuLayout?.isVisible = isMenuOpen
        }else{
            isMenuOpen = true
        }
    }

    override fun onAnimationRepeat(p0: Animation?) = Unit
}