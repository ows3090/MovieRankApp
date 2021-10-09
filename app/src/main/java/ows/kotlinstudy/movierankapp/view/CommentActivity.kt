package ows.kotlinstudy.movierankapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ows.kotlinstudy.movierankapp.Constants.COMMENTLIST
import ows.kotlinstudy.movierankapp.Constants.MOVIEINFO
import ows.kotlinstudy.movierankapp.R
import ows.kotlinstudy.movierankapp.adapter.recyclerview.CommentAdapter
import ows.kotlinstudy.movierankapp.databinding.ActivityCommentBinding
import ows.kotlinstudy.movierankapp.response.Comment
import ows.kotlinstudy.movierankapp.response.Movie
import ows.kotlinstudy.movierankapp.response.MovieCommentResponse
import java.text.DecimalFormat

class CommentActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCommentBinding
    private val movieInfo by lazy { intent.getParcelableExtra<Movie>(MOVIEINFO)!! }
    private val commentsInfo by lazy { intent.getParcelableExtra<MovieCommentResponse>(COMMENTLIST)!!}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment)
        initViews()
        bindViews()
    }

    private fun initViews() = with(binding){
        movieTitleTextView.text = movieInfo.title
        totalCountTextView.text = "${DecimalFormat("#,###").format(commentsInfo.totalCount)}명 참여"
        ratingTextView.text = "${movieInfo.userRating}"
        ratingBar.rating = movieInfo.userRating.toFloat()
        setGradeImageView()
    }

    private fun setGradeImageView() = with(binding){
        when(movieInfo.grade){
            12 -> Glide.with(gradeImageView.context)
                .load(R.drawable.ic_12)
                .into(gradeImageView)

            15 -> Glide.with(gradeImageView.context)
                .load(R.drawable.ic_15)
                .into(gradeImageView)

            19 -> Glide.with(gradeImageView.context)
                .load(R.drawable.ic_19)
                .into(gradeImageView)

            else -> Glide.with(gradeImageView.context)
                .load(R.drawable.ic_all)
                .into(gradeImageView)
        }
    }

    private fun bindViews() = with(binding){
        commentRecyclerView.run {
            layoutManager = LinearLayoutManager(this@CommentActivity)
            adapter = CommentAdapter()
            commentsInfo?.result.let {
                (adapter as CommentAdapter).addItems(it as ArrayList<Comment>)
                (adapter as CommentAdapter).notifyDataSetChanged()
            }
        }
    }
}