package com.example.test.utils
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context

import android.view.*

import android.widget.Toast

import com.example.test.R

import java.util.*
import android.text.TextUtils
import android.util.Patterns
import java.io.IOException
import java.text.SimpleDateFormat


class Utils {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null

        private const val MOBILE_NO_PATTERN = "^[+]?[0-9]{10,13}$"

        var customDialog: Dialog? = null




        @SuppressLint("InflateParams")
        fun showProgressDialog(activity: Activity) {
            if (customDialog != null) {
                customDialog = null
            }
            customDialog = Dialog(activity, R.style.CustomDialogNew)
            customDialog!!.setCancelable(false)
            customDialog!!.setCanceledOnTouchOutside(false)
            Objects.requireNonNull(customDialog!!.window)!!
                .setBackgroundDrawableResource(R.color.transparent)
            val inflater = LayoutInflater.from(activity)
            val mView: View = inflater.inflate(R.layout.progress_dialog, null)
            customDialog!!.setContentView(mView)
            if (!customDialog!!.isShowing) {
                customDialog!!.show()
                println("progress dialog shown $activity")
            } else {
                customDialog!!.dismiss()
            }
        }

        fun showCustomDialog(context: Context?, resId: Int): Dialog {
            val dialog = Dialog(context!!)
            Objects.requireNonNull(dialog.window)!!
                .setBackgroundDrawableResource(android.R.color.transparent)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(resId)
            val windo = dialog.window
            windo!!.setDimAmount(0.7f)
            val wlp = windo.attributes
            wlp.gravity = Gravity.CENTER
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT
            windo.attributes = wlp
            return dialog
        }


        fun hideProgressDialog(activity: Activity) {
            if (customDialog != null &&
                customDialog!!.isShowing
                && !activity.isFinishing
            ) {
                customDialog!!.dismiss()
                println("progress dialog dismissed $activity")
            }
        }
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

    }
}