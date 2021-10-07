package ows.kotlinstudy.movierankapp.adapter.recyclerview

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ows.kotlinstudy.movierankapp.databinding.GalleryItemBinding
import ows.kotlinstudy.movierankapp.repository.Url.GALLERY_DEFAULT_URL
import ows.kotlinstudy.movierankapp.repository.Url.VIDEO_URL
import ows.kotlinstudy.movierankapp.view.GalleryActivity

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private val galleries = ArrayList<String>()

    inner class GalleryViewHolder(val binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            binding.playImageView.isVisible = url.contains(VIDEO_URL)
            if (binding.playImageView.isVisible) {
                Glide.with(binding.galleryImageView.context)
                    .load(GALLERY_DEFAULT_URL + url.substring(17) + "/0.jpg")
                    .into(binding.galleryImageView)

                binding.galleryImageView.setOnClickListener {
                    binding.galleryImageView.context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(url)
                        )
                    )
                }
            } else {
                Glide.with(binding.galleryImageView.context)
                    .load(url)
                    .into(binding.galleryImageView)

                binding.galleryImageView.setOnClickListener {
                    binding.galleryImageView.context.startActivity(
                        Intent(
                            binding.galleryImageView.context,
                            GalleryActivity::class.java
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            GalleryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(galleries.get(position))
    }

    override fun getItemCount(): Int = galleries.size

    fun clearItems() = galleries.clear()

    fun addGalleryItems(items: ArrayList<String>) {
        galleries.addAll(items)
    }

    fun addMovieItems(items: ArrayList<String>) {
        galleries.addAll(items)
    }
}