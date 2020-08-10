package com.example.Home_step1

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


class PagerPhotoListAdapter : ListAdapter<PhotoTtem, PagerPhotoViewHolder>(DiffCallback) {
    private var isplaying:Boolean =true
    object DiffCallback:DiffUtil.ItemCallback<PhotoTtem>(){
        override fun areItemsTheSame(oldItem: PhotoTtem, newItem: PhotoTtem): Boolean {
            return  oldItem===newItem
        }

        override fun areContentsTheSame(oldItem: PhotoTtem, newItem: PhotoTtem): Boolean {
            return oldItem.photoID==newItem.photoID
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.pager_photo_view,parent,
        false).apply {
            val holder=PagerPhotoViewHolder(this)
            return holder

        }
    }

    override fun onBindViewHolder(holder: PagerPhotoViewHolder, position: Int) {
        holder.itemView.iv_play.setImageResource(R.drawable.play)
        holder.itemView.iv_play.setOnClickListener{
            if(isplaying){
                holder.itemView.iv_play.setImageResource(R.drawable.stop)
            }else{
                holder.itemView.iv_play.setImageResource(R.drawable.play)
            }
            isplaying=!isplaying
        }
    }

}
class PagerPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
