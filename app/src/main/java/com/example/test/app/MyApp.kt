package com.example.test.app

import android.os.Build
import android.os.StrictMode
import androidx.multidex.MultiDexApplication

class MyApp : MultiDexApplication() {


    companion object{
        var instance: MyApp? = null
        private var activityVisible = false

        @JvmName("getInstance1")
        fun getInstance(): MyApp? {
            if (instance == null) instance = MyApp()
            return instance
        }

        fun activityResumed() {
            activityVisible = true
        }

        fun activityPaused() {
            activityVisible = false
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this@MyApp
        try {
            enableStricMode()
            /**
             * init retrofit client to call network services
             */
//            RetrofitHolder retrofitHolder = new RetrofitHolder(instance);
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        /*
         *
         * Initilizing Dao for the app
         *
         * */
    }



    private fun enableStricMode() {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                try {
                    val m = StrictMode::class.java.getMethod("disableDeathOnFileUriExposure")
                    m.invoke(null)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}