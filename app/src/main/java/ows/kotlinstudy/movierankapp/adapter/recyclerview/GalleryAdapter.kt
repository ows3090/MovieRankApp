package ows.kotlinstudy.movierankapp.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ows.kotlinstudy.movierankapp.databinding.GalleryViewBinding
import ows.kotlinstudy.movierankapp.repository.Url.GALLERY_DEFAULT_URL

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private val galleries = ArrayList<String>()

    inner class GalleryViewHolder(val binding : GalleryViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(url : String){
            binding.playImageView.isVisible = url.contains(GALLERY_DEFAULT_URL)

            Glide.with(binding.galleryImageView.context)
                .load(url)
                .into(binding.galleryImageView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(GalleryViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(galleries.get(position))
    }

    override fun getItemCount(): Int = galleries.size

    fun clearItems() = galleries.clear()

    fun addGalleryItems(items : ArrayList<String>){
        galleries.addAll(items)
    }

    fun addMovieItems(items : ArrayList<String>){
        galleries.addAll(items)
    }
}