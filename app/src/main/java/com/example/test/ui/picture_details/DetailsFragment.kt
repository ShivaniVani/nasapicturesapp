package com.example.test.ui.picture_details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.test.ui.activity.MainActivity
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentDetailsBinding
import com.example.test.ui.pictures_list.Pictures
import com.example.test.utils.Utils
import com.google.android.material.transition.MaterialSharedAxis
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipeViewBuilder


class DetailsFragment : BaseFragment(), View.OnClickListener {
    private lateinit var _activity: MainActivity
    private lateinit var _binding: FragmentDetailsBinding
    var utils: Utils? = null
    var url: String? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) _activity = context
    }

    @SuppressLint("SoonBlockedPrivateApi")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun initializeViews(rootView: View?) {
        // transition
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        //init
        utils = Utils()
        //get bundle
        url=arguments?.getString("url")

        _binding.swipeView.getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
            .setDisplayViewCount(3)
            .setSwipeDecor(
                SwipeDecor()
                    .setPaddingTop(20)
                    .setRelativeScale(0.01f)
            )

        setPicturesDetails()

    }

    private fun setPicturesDetails()
    {
        val jsonFileString = Utils.getJsonDataFromAsset(_activity, "pictures.json")
        Log.i("data", jsonFileString!!)
        val gson = Gson()
        val listPicturesType = object : TypeToken<List<Pictures>>() {}.type
        val pictures: List<Pictures> = gson.fromJson(jsonFileString, listPicturesType)
        // for adding the first card as the selected pictures card
        pictures.forEach {  pictures ->
            if(pictures.url.equals(url,ignoreCase = false))
            {
                _binding.swipeView.addView(SwipeCard(_activity, pictures, _binding.swipeView))
            }
        }
        pictures.forEach { pictures ->
            if(!pictures.url.equals(url,ignoreCase = false))
            {
                _binding.swipeView.addView(SwipeCard(_activity, pictures, _binding.swipeView))
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        initializeViews(_binding.root)
        return _binding.root
    }

}