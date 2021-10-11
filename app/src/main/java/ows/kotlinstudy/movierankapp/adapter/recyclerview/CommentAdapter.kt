package ows.kotlinstudy.movierankapp.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ows.kotlinstudy.movierankapp.databinding.CommentItemBinding
import ows.kotlinstudy.movierankapp.repository.model.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    private val comments = ArrayList<Comment>()

    inner class CommentViewHolder(val binding : CommentItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(comment : Comment){
            Glide.with(binding.userImageView.context)
                .load(comment.writerImage)
                .into(binding.userImageView)

            with(binding){
                userIdTextView.text = comment.writer
                commentRatingBar.rating = comment.rating.toFloat()
                commentTextView.text = comment.contents
                recommendResultTextView.text = "${comment.recommend}"
                timeTextView.text = "${(System.currentTimeMillis()/1000 - comment.timestamp)/3600}분전"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(CommentItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments.get(position))
    }

    override fun getItemCount(): Int = comments.size

    fun addItems(items : ArrayList<Comment>){
        comments.clear()
        comments.addAll(items)
    }
}