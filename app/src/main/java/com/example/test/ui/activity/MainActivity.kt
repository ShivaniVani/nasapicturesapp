package com.example.test.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.test.base.BaseActivity
import com.example.test.ui.pictures.pictures_list.PicturesListFragment
import com.example.test.databinding.ActivityMainBinding
import com.example.test.utils.*
import com.example.test.R
import com.example.test.ui.pictures.picture_details.DetailsFragment

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeViews()
    }

    override fun onBackPressed() {
            val fragment =
                navHostFragmentDelegate.childFragmentManager.fragments.get(0) as Fragment
            Log.e(javaClass.name, "onBackPressed: ")
            val currentFragment = fragment is PicturesListFragment
            if (currentFragment || fragment is PicturesListFragment)
                exitApp()
            else if (fragment is DetailsFragment)
                navHostFragmentDelegate.navController
                    .navigate(R.id.list_fragment)
            else
                super.onBackPressed()
    }

    override fun initializeViews() {


        _binding!!.abLayout.ivBack.setOnClickListener {
            onBackPressed()
        }

    }



    fun hideToolbar() {
        _binding!!.abLayout.clMainContent.visibility = View.GONE
    }


    fun setTile(title: String) {
        _binding!!.abLayout.tvTitle.setText(title)
    }

    fun showToolbar(isShowLogo: Boolean) {
        _binding!!.abLayout.clMainContent.visibility = View.VISIBLE
        if (isShowLogo) {
            _binding!!.abLayout.ivLogo.visibility = View.VISIBLE
            _binding!!.abLayout.tvTitle.visibility = View.GONE
            _binding!!.abLayout.ivBack.visibility = View.GONE
        } else {
            _binding!!.abLayout.ivLogo.visibility = View.GONE
            _binding!!.abLayout.tvTitle.visibility = View.VISIBLE
            _binding!!.abLayout.ivBack.visibility = View.VISIBLE
        }
    }


    private fun exitApp() {
        val dialog = Utils.showCustomDialog(this, R.layout.exit_dialog)
        val ok = dialog.findViewById<View>(R.id.btn_ok) as TextView
        ok.setOnClickListener {
            ok.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
            dialog.dismiss()
            finishAffinity()
        }
        val cancel = dialog.findViewById<View>(R.id.btn_cancel) as TextView
        cancel.setOnClickListener {
            cancel.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
            dialog.dismiss()
        }
        dialog.show()
    }

}