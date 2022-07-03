package com.example.test.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

open class BaseViewModel<N> : ViewModel() {

    private var mNavigator: WeakReference<N>? = null

    fun BaseViewModel() {}

    fun getmNavigator(): WeakReference<N>? {
        return mNavigator
    }
}