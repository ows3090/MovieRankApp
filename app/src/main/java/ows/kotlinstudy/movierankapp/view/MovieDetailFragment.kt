package ows.kotlinstudy.movierankapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.databinding.FragmentMoviedetailBinding

class MovieDetailFragment : Fragment() {

    private var binding : FragmentMoviedetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentMovieDetailBinding: FragmentMoviedetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_moviedetail, container, false)
        binding = fragmentMovieDetailBinding
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}