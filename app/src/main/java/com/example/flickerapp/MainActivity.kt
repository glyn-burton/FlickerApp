package com.example.flickercodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickercodetest.datasource.remote.OkHttpHelper
import com.example.flickercodetest.model.PhotoResponse.PhotoResponse
import com.example.flickercodetest.view.PhotoAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserResponse(userResponse : PhotoResponse){

        rvResults.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rvResults.addItemDecoration(itemDecoration)
        rvResults.adapter = PhotoAdapter(userResponse.photos.photo)
        (rvResults.adapter as PhotoAdapter).updateList()

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }


    fun onClick(view: View) {

        SEARCH_TERM = etPhotoTag.text.toString()
        val url = "https://www.flickr.com/services/rest/?method=flickr.photos." +
                "search&api_key=6bf318919bbbc455f3573d18798a58e3&tags=$SEARCH_TERM&format=json&nojsoncallback=1"
        val okHttpHelper = OkHttpHelper()
        okHttpHelper.makeAsyncAPI(url)

    }
}
