package com.example.pagegallery

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.gallery_cell.view.*

class GalleryAdapter:ListAdapter<PhotoTtem,MyViewHolder>(DIFFCALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder=MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.gallery_cell,parent,false))
        holder.itemView.setOnClickListener{
            holder.itemView.setOnClickListener{
                Bundle().apply {
                    putParcelableArrayList("photo_list", ArrayList(currentList))
                    putInt("photo_position",holder.adapterPosition)
                    holder.itemView.findNavController().navigate(R.id.action_galleryFragment_to_pagerPhotoFragment,this)
                }

            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.textView.text="发布者："+getItem(position).username
        holder.itemView.shimmerlayoutcell.apply {
            setShimmerColor(0x55ffffff)
            setShimmerAngle(0)
            startShimmerAnimation()
        }
        Glide.with(holder.itemView.imageView)
            .load(getItem(position).previewUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .listener(object :RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false.also { holder.itemView.shimmerlayoutcell?.stopShimmerAnimation() }
                }
            })
            .into(holder.itemView.imageView)
    }
    object DIFFCALLBACK :DiffUtil.ItemCallback<PhotoTtem>(){
        override fun areItemsTheSame(oldItem: PhotoTtem, newItem: PhotoTtem): Boolean {
            return oldItem===newItem
        }

        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }

        override fun areContentsTheSame(oldItem: PhotoTtem, newItem: PhotoTtem): Boolean {
            return oldItem.photoID==newItem.photoID
        }
    }


}
class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

}