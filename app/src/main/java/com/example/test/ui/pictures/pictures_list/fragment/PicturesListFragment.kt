package com.example.test.ui.pictures.pictures_list.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import com.example.test.R
import com.example.test.app.MyApp
import com.example.test.base.BaseFragment
import com.google.android.material.transition.MaterialSharedAxis
import com.example.test.ui.activity.MainActivity
import com.example.test.ui.pictures.pictures_list.adapter.PictureListAdapter
import com.example.test.databinding.FragmentHomeGridBinding
import com.example.test.ui.pictures.viewmodel.SharedViewModel
import com.example.test.ui.viewModelFactory.ViewModelFactory
import com.example.test.utils.Connectivity
import com.example.test.utils.Utils


class PicturesListFragment : BaseFragment(), PictureListAdapter.onItemClick {

    private lateinit var _activity: MainActivity
    private lateinit var _binding: FragmentHomeGridBinding
    private var adapter: PictureListAdapter? = null
    var viewModel: SharedViewModel? = null

    override fun initializeViews(rootView: View?) {

        //transition
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

        //setupViewModel
        setupViewModel()
        // start shimmer
        _binding.shimmerViewContainer.startShimmer()

        // get the list of pictures from json
        if (Connectivity.isConnected()){
            viewModel!!.getPicturesList(_activity)
            getPictures()


        }
        else{
            // stop shimmer
            _binding.shimmerViewContainer.stopShimmer()
            _binding.shimmerViewContainer.visibility = View.GONE

                // show no internet image
            _binding.ivNoInternet.visibility=View.VISIBLE
            _binding.rvPictures.visibility=View.GONE
            Utils.showToast(_activity,getString(R.string.no_internet))
        }


    }

    // to get the list of pictures and pass it to the adapter
    private fun getPictures() {
        viewModel!!.responsePictures.observe(viewLifecycleOwner, {
            adapter = PictureListAdapter(_activity, this, it)
            _binding.rvPictures.setHasFixedSize(true)
            _binding.rvPictures.adapter = adapter
        })

        // stop shimmer and display pictures
        Handler().postDelayed({
            // stop shimmer after delay
            _binding.rvPictures.visibility=View.VISIBLE
            _binding.shimmerViewContainer.stopShimmer()
            _binding.shimmerViewContainer.visibility = View.GONE
        }, 1000)
    }
// set up the viewModel
    private fun setupViewModel() {
        val factory =
            ViewModelFactory(
                MyApp.instance!!)
        viewModel = ViewModelProvider(this, factory).get(SharedViewModel::class.java)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) _activity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeGridBinding.inflate(inflater, container, false)
        initializeViews(_binding.root)
        return _binding.root
    }

    override fun onItemClick(url: String) {
        val bundle = Bundle()
        bundle.putString("url", url)
        val options = NavOptions.Builder().setPopUpTo(R.id.detail_fragment, true).build()
        navigateTo(
            R.id.action_home_to_detail,
            options, bundle
        )
    }

}