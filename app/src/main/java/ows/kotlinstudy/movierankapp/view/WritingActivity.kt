package ows.kotlinstudy.movierankapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.coroutines.Job
import ows.kotlinstudy.movierankapp.Constants.MOVIEINFO
import ows.kotlinstudy.movierankapp.MovieApplication
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.adapter.ActivityScope
import ows.kotlinstudy.movierankapp.dagger.module.ActivityModule
import ows.kotlinstudy.movierankapp.databinding.ActivityWritingBinding
import ows.kotlinstudy.movierankapp.repository.model.Movie
import ows.kotlinstudy.movierankapp.viewmodel.ViewModelFactory
import ows.kotlinstudy.movierankapp.viewmodel.WritingViewModel
import javax.inject.Inject

class WritingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWritingBinding
    private val movieInfo by lazy { intent.getParcelableExtra<Movie>(MOVIEINFO)!! }
    private lateinit var writingViewModel: WritingViewModel

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_writing)

        initViewModel()
        binding.apply {
            lifecycleOwner = this@WritingActivity
            viewmodel = writingViewModel
        }

        initViews()
        bindViews()
    }

    private fun initViewModel() {
        (application as MovieApplication).getAppComponent().getActivityBuilder()
            .setModule(ActivityModule())
            .build()
            .inject(this)

        writingViewModel =
            ViewModelProvider(this, viewmodelFactory).get(WritingViewModel::class.java)
    }

    private fun initViews() = with(binding) {
        movieTitleTextView.text = movieInfo.title

        when (movieInfo.grade) {
            12 -> movieGradeImageView.setImageResource(R.drawable.ic_12)
            15 -> movieGradeImageView.setImageResource(R.drawable.ic_15)
            19 -> movieGradeImageView.setImageResource(R.drawable.ic_19)
            else -> movieGradeImageView.setImageResource(R.drawable.ic_all)
        }
    }

    private fun bindViews() = with(binding) {
        saveButton.setOnClickListener {
            writingViewModel.requestAddcomment(movieInfo.id)
        }
        cancelButton.setOnClickListener { finish() }

        writingViewModel.finishLiveData.observe(this@WritingActivity, {
            if(it){
                Toast.makeText(this@WritingActivity, "댓글저장이 완료되었습니다", Toast.LENGTH_LONG).show()
                setResult(RESULT_OK)
                finish()
            }
        })
    }


}