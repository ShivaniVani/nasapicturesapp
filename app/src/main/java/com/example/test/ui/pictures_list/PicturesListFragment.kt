package com.example.test.ui.pictures_list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import com.example.test.R
import com.example.test.base.BaseFragment
import com.google.android.material.transition.MaterialSharedAxis
import com.example.test.ui.activity.MainActivity
import com.example.test.ui.pictures_list.adapter.UserListAdapter
import com.example.test.databinding.FragmentHomeGridBinding
import com.example.test.utils.Utils
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson


class PicturesListFragment : BaseFragment(),UserListAdapter.onItemClick {

    private lateinit var _activity: MainActivity
    private var _binding: FragmentHomeGridBinding? = null
    private var adapter: UserListAdapter? = null

    override fun initializeViews(rootView: View?) {

        //transition
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

        // get the list of pictures from json
        getPicturesList()

    }

    private fun getPicturesList()
    {
        val jsonFileString = Utils.getJsonDataFromAsset(_activity, "pictures.json")
        Log.i("data", jsonFileString.toString())
        val gson = Gson()
        val listPicturesType = object : TypeToken<List<Pictures>>() {}.type
        val pictures: List<Pictures> = gson.fromJson(jsonFileString, listPicturesType)
        pictures.forEachIndexed { idx, pictures ->
            Log.i("data", "> Item $idx:\n$pictures")
        }

        adapter = UserListAdapter(_activity,this,pictures)
        _binding!!.rvPictures.setHasFixedSize(true)
        _binding!!.rvPictures.adapter = adapter

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
        initializeViews(_binding!!.root)
        return _binding!!.root
    }

    override fun onItemClick(url: String) {
        val bundle = Bundle()
        bundle.putString("url",url)
        val options = NavOptions.Builder().setPopUpTo(R.id.detail_fragment, true).build()
        // check if the user is logged in or not
        navigateTo(
            R.id.action_home_to_detail,
            options
        ,bundle
        )    }

}