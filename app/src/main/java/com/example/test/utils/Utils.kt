package com.example.test.utils
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.*
import android.widget.Toast
import java.util.*
import java.io.IOException


class Utils {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null

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
        fun showToast(context: Context,msg:String)
        {
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
        }


    }
}