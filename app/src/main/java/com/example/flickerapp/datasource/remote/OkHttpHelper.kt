package com.example.flickercodetest.datasource.remote

import android.util.Log
import com.example.flickercodetest.model.PhotoResponse.PhotoResponse
import com.google.gson.Gson
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeUnit

class OkHttpHelper() {

    private fun getClient(): OkHttpClient{
        val okHttpClient = OkHttpClient.Builder().build()
        return okHttpClient

    }

    fun makeAsyncAPI(url:String){

        val request = Request.Builder().url(url).build()
        getClient().run {
            newCall(request).enqueue(object: Callback{

                override fun onResponse(call: Call, response: Response) {
                    val json = response.body?.string()
                    Log.d("TAG", json)
                    val userResults = Gson().fromJson<PhotoResponse>(json,PhotoResponse::class.java)
                    Log.d("TAG", userResults.toString())
                    EventBus.getDefault().post(userResults)
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.e("ERROR TAG","Error in makeAsyncAPICall ----->",e)

                }




            })

        }
    }
}