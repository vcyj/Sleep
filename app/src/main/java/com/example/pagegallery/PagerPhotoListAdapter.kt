package com.example.pagegallery

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.gallery_cell.view.*
import kotlinx.android.synthetic.main.pager_photo_view.view.*


class PagerPhotoListAdapter:ListAdapter<PhotoTtem,PagerPhotoViewHolder>(DiffCallBack) {
    object DiffCallBack:DiffUtil.ItemCallback<PhotoTtem>(){
        override fun areItemsTheSame(oldItem: PhotoTtem, newItem: PhotoTtem): Boolean {
            return oldItem===newItem
        }

        override fun equals(other: Any?): Boolean {
            return super.equals(other)
        }

        override fun getChangePayload(oldItem: PhotoTtem, newItem: PhotoTtem): Any? {
            return super.getChangePayload(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: PhotoTtem, newItem: PhotoTtem): Boolean {
            return oldItem.photoID==newItem.photoID
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.pager_photo_view,parent,false).apply {
            return PagerPhotoViewHolder(this)

        }
    }

    override fun onBindViewHolder(holder: PagerPhotoViewHolder, position: Int) {
        Glide.with(holder.itemView.pagerPhoto)
            .load(getItem(position).fullUrl)
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
            .into(holder.itemView.pagerPhoto)
    }

}
class PagerPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)