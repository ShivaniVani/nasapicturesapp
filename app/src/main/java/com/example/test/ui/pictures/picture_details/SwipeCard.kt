package com.example.test.ui.pictures.picture_details

import com.example.test.R
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState
import com.mindorks.placeholderview.annotations.swipe.SwipeInState
import com.mindorks.placeholderview.annotations.swipe.SwipeIn
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState
import com.mindorks.placeholderview.annotations.swipe.SwipeOut
import com.bumptech.glide.Glide
import com.mindorks.placeholderview.SwipePlaceHolderView
import android.widget.TextView
import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.example.test.ui.pictures.pictures_list.model.Pictures
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View


@Layout(R.layout.fragment_details_swipe)
public class SwipeCard(val context: Context?, val pictures: Pictures?, val swipeView: SwipePlaceHolderView?) {
    @View(R.id.ivPicture)
    private val ivPicture: ImageView? = null

    @View(R.id.tvDate)
    private val tvDate: TextView? = null

    @View(R.id.tvCopyRight)
    private val tvCopyRight: TextView? = null

    @View(R.id.tvTitle)
    private val tvTitle: TextView? = null


    @Resolve
    private fun onResolved() {
        Glide.with(context!!).load(pictures!!.url).into(ivPicture!!)
        tvDate!!.text=pictures.date
        tvCopyRight!!.text=pictures.copyright
        tvTitle!!.text=pictures.title+"\n"+pictures.explanation
    }

    @SwipeOut
    private fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
        swipeView!!.addView(this)
    }

    @SwipeCancelState
    private fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    private fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
    }

    @SwipeInState
    private fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")
    }

    @SwipeOutState
    private fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }

}