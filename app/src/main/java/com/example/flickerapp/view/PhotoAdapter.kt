package com.example.flickercodetest.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickercodetest.PicDescriptionActivity
import com.example.flickercodetest.R
import com.example.flickercodetest.model.PhotoResponse.Photo
import kotlinx.android.synthetic.main.picture_item.view.*

class PhotoAdapter (var pictureList: List<Photo>): RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false)))

        override fun getItemCount(): Int = pictureList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int)
        {
            holder.populateItem(pictureList[position])
        }

        fun updateList()
        {
            notifyDataSetChanged()
        }

        inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
        {
            fun populateItem(picture : Photo)
            {
                itemView.tvImageTitle.text = picture.title
                var pictureUrl = "https://farm${picture.farm}.staticflickr.com/${picture.server}/${picture.id}_${picture.secret}.jpg"
                Glide
                    .with(itemView)
                    .load(pictureUrl)
                    .into(itemView.imPictureView)
                itemView.setOnClickListener {
                    val intent = Intent(it.context, PicDescriptionActivity::class.java)
                    intent.putExtra("KEY", picture)
                    it.context.startActivity(intent)
                }
            }

        }
    }

