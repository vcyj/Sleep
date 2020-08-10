package com.example.Home_step1

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoListLive=MutableLiveData<List<PhotoTtem>>()
    val photoListLive:LiveData<List<PhotoTtem>>
    get() = _photoListLive
    fun fetchData(){
        val stringRequest=StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener {
                _photoListLive.value=Gson().fromJson(it,Pixabay::class.java).hits.toList()
            },
            Response.ErrorListener { Log.d("TAG", "fetchData: ") }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }
    fun getUrl(): String {
        return "https://pixabay.com/api/?key=17767924-38454c05f11829d76529f9c42&q=${keywords.random()}"
    }
    private val keywords= arrayOf("cat","girl","car","beauty","car","phone","animal")
}