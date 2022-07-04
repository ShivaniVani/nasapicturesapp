package com.example.test.ui.pictures.picture_details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.test.R
import com.example.test.app.MyApp
import com.example.test.ui.activity.MainActivity
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentDetailsBinding
import com.example.test.ui.pictures.viewmodel.SharedViewModel
import com.example.test.ui.viewModelFactory.ViewModelFactory
import com.example.test.utils.Connectivity
import com.example.test.utils.Utils
import com.google.android.material.transition.MaterialSharedAxis
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipeViewBuilder


class DetailsFragment : BaseFragment(), View.OnClickListener {
    private lateinit var _activity: MainActivity
    private lateinit var _binding: FragmentDetailsBinding
    var utils: Utils? = null
    var url: String? = null
    var viewModel: SharedViewModel? = null


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

        //setupViewModel
        setupViewModel()

        // init swipe view
        _binding.swipeView.getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
            .setDisplayViewCount(3)
            .setSwipeDecor(
                SwipeDecor()
                    .setPaddingTop(20)
                    .setRelativeScale(0.01f)
            )

        // check internet connectivity
        if (Connectivity.isConnected()){
            viewModel!!.getPicturesList(_activity)
            setPicturesDetails()
        }
        else{
            // show image for no internet connection
            _binding.ivNoInternet.visibility=View.VISIBLE
            _binding.swipeView.visibility=View.GONE
            Utils.showToast(_activity,getString(R.string.no_internet))
        }

    }
    // set up the viewModel
    private fun setupViewModel() {
        val factory =
            ViewModelFactory(
                MyApp.instance!!)
        viewModel = ViewModelProvider(this, factory).get(SharedViewModel::class.java)
    }


    // set data into  view
    private fun setPicturesDetails()
    {

        viewModel!!.responsePictures.observe(viewLifecycleOwner, {
            // for adding the first card as the selected pictures card
            it.forEach {  pictures ->
                if(pictures.url.equals(url,ignoreCase = false))
                    _binding.swipeView.addView(SwipeCard(_activity, pictures, _binding.swipeView))
            }
            // for adding other cards
            //NOTE : Not passed position here because the method is set to protected and can't access it from here
            it.forEach { pictures ->
                if(!pictures.url.equals(url,ignoreCase = false))
                    _binding.swipeView.addView(SwipeCard(_activity, pictures, _binding.swipeView))
            }
        })


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