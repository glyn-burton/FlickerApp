package com.example.flickercodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.flickercodetest.model.PhotoResponse.Photo
import kotlinx.android.synthetic.main.activity_pic_description.*
import kotlinx.android.synthetic.main.picture_item.*

class PicDescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic_description)
        val passedPhoto = intent?.getParcelableExtra<Photo>("KEY")
        Log.d("TAG",passedPhoto?.title)
        tvPictureTitle.text = "Title: "+ passedPhoto?.title
        tvAuthor.text = "Owner of photo: " + passedPhoto?.owner
        tvIsPublic.text = "Is this photo public?: " + passedPhoto?.ispublic
        var pictureUrl = "https://farm${passedPhoto?.farm}.staticflickr.com/${passedPhoto?.server}/${passedPhoto?.id}_${passedPhoto?.secret}.jpg"
        Glide
            .with(this)
            .load(pictureUrl)
            .into(imImage)
    }

}
