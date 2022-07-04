package com.example.test.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import com.example.test.R
import com.example.test.ui.activity.MainActivity
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentSplashBinding
import com.google.android.material.transition.MaterialSharedAxis

class SplashFragment : BaseFragment() {

    private var _activity: MainActivity? = null
    private var _binding: FragmentSplashBinding? = null

    override fun initializeViews(rootView: View?) {

        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

        Handler().postDelayed({
            val options = NavOptions.Builder().setPopUpTo(R.id.splash_fragment, true).build()
            // check if the user is logged in or not
            navigateTo(
                    R.id.action_splash_to_nav_home,
                options
            )
        }, 2000)

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
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        initializeViews(_binding!!.root)
        return _binding!!.root
    }

}