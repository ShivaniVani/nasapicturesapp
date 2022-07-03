package com.example.test.ui.pictures.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.test.ui.pictures.pictures_list.model.Pictures
import com.example.test.utils.Utils
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SharedViewModel (application: Application) :
AndroidViewModel(application), Observable {

    val responsePictures =
        MutableLiveData<List<Pictures>>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun getPicturesList(context:Context) {
        uiScope.launch {
            val jsonFileString = Utils.getJsonDataFromAsset(context, "pictures.json")
            Log.i("data", jsonFileString.toString())
            val gson = Gson()
            val listPicturesType = object : TypeToken<List<Pictures>>() {}.type
            val pictures: List<Pictures> = gson.fromJson(jsonFileString, listPicturesType)
            pictures.forEachIndexed { idx, pictures ->
                Log.i("data", "> Item $idx:\n$pictures")
            }
            responsePictures.postValue(pictures)
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        TODO("Not yet implemented")
    }


}