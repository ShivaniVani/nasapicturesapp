package com.example.test.ui.pictures.picture_details

import android.annotation.SuppressLint
import com.example.test.R
import com.mindorks.placeholderview.annotations.Layout
import com.bumptech.glide.Glide
import com.mindorks.placeholderview.SwipePlaceHolderView
import android.widget.TextView
import android.content.Context
import android.widget.ImageView
import com.example.test.ui.pictures.pictures_list.model.Pictures
import com.example.test.utils.Utils
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View


@SuppressLint("NonConstantResourceId")
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


    @SuppressLint("SetTextI18n")
    @Resolve
    private fun onResolved() {
        Glide.with(context!!).load(pictures!!.url).into(ivPicture!!)
        tvDate!!.text= Utils.dateParse(pictures.date)
        tvCopyRight!!.text=pictures.copyright
        tvTitle!!.text=pictures.title+"\n"+pictures.explanation
    }

}