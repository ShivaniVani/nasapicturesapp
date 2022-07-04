package com.example.test.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.example.test.R
import com.example.test.ui.activity.MainActivity
import com.example.test.ui.pictures.picture_details.DetailsFragment
import com.example.test.ui.pictures.pictures_list.fragment.PicturesListFragment

abstract class BaseFragment : Fragment(), View.OnClickListener {
    abstract fun initializeViews(rootView: View?)

    private val TAG = "BaseFragment";
    var mActivity: MainActivity? = null

    open fun replaceFragment(fragment: Fragment, container: Int, isBackStack: Boolean) {
        val manager = requireActivity().supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(container, fragment)
        if (isBackStack) {
            val stack_name = fragment.javaClass.name
            transaction.addToBackStack(stack_name)
        }
        transaction.commit()
    }

    open fun replaceFragment(fragment: Fragment, isBackStack: Boolean) {
        val manager = requireActivity().supportFragmentManager
        val transaction = manager.beginTransaction()
        //        transaction.replace(R.id.home_layout_container,fragment);
        if (isBackStack) {
            val stack_name = fragment.javaClass.name
            transaction.addToBackStack(stack_name)
        }
        transaction.commit()
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    fun navigateTo(navigationId: Int, options: NavOptions?) {
        val navHostController = NavHostFragment.findNavController(this)
        navHostController.navigate(navigationId, null, options)
    }

    fun navigateTo(navigationId: Int, options: NavOptions, bundle: Bundle) {
        val navHostController = NavHostFragment.findNavController(this)
        navHostController.navigate(navigationId, bundle, options)
    }


    private fun checkForToolbar() {
        if (this is PicturesListFragment) mActivity?.showToolbar(true)
        else if (this is DetailsFragment) mActivity?.showToolbar(
            false
        )
        else mActivity?.hideToolbar()
    }

    private fun checkForTitle() {
        if (this is DetailsFragment) mActivity?.setTile(getString(R.string.details))
   }

    override fun onStart() {
        Log.e(TAG, "onStart: ")
        super.onStart()
        checkForToolbar()
        checkForTitle()
    }


    override fun onResume() {
        super.onResume()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mActivity = context
        }
    }

    override fun onClick(p0: View?) {
    }

}