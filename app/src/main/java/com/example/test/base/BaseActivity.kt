package com.example.test.base

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.test.R
import com.example.test.app.MyApp

abstract class BaseActivity :AppCompatActivity(), View.OnClickListener {

    val navHostFragmentDelegate: NavHostFragment get() = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    /**
     * this method is responsible to initialize the views
     */
    abstract fun initializeViews()

    override fun onResume() {
        super.onResume()
        MyApp.activityResumed()
    }

    override fun onPause() {
        super.onPause()
        MyApp.activityPaused()
    }

    override fun onClick(p0: View?) {
    }
}