package com.example.test.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.telephony.TelephonyManager

import com.example.test.app.MyApp


class Connectivity {

    /**
     * Get the network info
     */
    @SuppressLint("MissingPermission")
    companion object Test {
        fun getNetworkInfo(context: Context): NetworkInfo {
            val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo!!
        }


        public fun isConnected(): Boolean {
            val connectivityManager = MyApp.getInstance()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val nw = connectivityManager.activeNetwork ?: return false
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                return when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            } else {
                val nwInfo = connectivityManager.activeNetworkInfo ?: return false
                return nwInfo.isConnected
            }
        }



        /**
         * Check if there is any connectivity to a Wifi network
         *
         * @param context
         * @return
         */
        fun isConnectedWifi(context: Context): Boolean {
            val info: NetworkInfo = getNetworkInfo(context)
            return info.isConnected && info.type == ConnectivityManager.TYPE_WIFI
        } //


        /**
         * Check if there is any connectivity to a mobile network
         *
         * @param context
         * @return
         */
        fun isConnectedMobile(context: Context): Boolean {
            val info: NetworkInfo = getNetworkInfo(context)
            return info.isConnected && info.type == ConnectivityManager.TYPE_MOBILE
        }

        /**
         * Check if there is fast connectivity
         *
         * @param context
         * @return
         */
        fun isConnectedFast(context: Context): Boolean {
            val info: NetworkInfo = getNetworkInfo(context)
            return info.isConnected && isConnectionFast(
                info.type,
                info.subtype
            )
        }

        /**
         * Check if the connection is fast
         *
         * @param type
         * @param subType
         * @return
         */
        fun isConnectionFast(type: Int, subType: Int): Boolean {
            return if (type == ConnectivityManager.TYPE_WIFI) {
                if (isConnectivity()) {
                    return true
                }
                return false
                true
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                when (subType) {
                    TelephonyManager.NETWORK_TYPE_1xRTT -> false // ~ 50-100 kbps
                    TelephonyManager.NETWORK_TYPE_CDMA -> false // ~ 14-64 kbps
                    TelephonyManager.NETWORK_TYPE_EDGE -> true // ~ 50-100 kbps
                    TelephonyManager.NETWORK_TYPE_EVDO_0 -> true // ~ 400-1000 kbps
                    TelephonyManager.NETWORK_TYPE_EVDO_A -> true // ~ 600-1400 kbps
                    TelephonyManager.NETWORK_TYPE_GPRS -> true // ~ 100 kbps
                    TelephonyManager.NETWORK_TYPE_HSDPA -> true // ~ 2-14 Mbps
                    TelephonyManager.NETWORK_TYPE_HSPA -> true // ~ 700-1700 kbps
                    TelephonyManager.NETWORK_TYPE_HSUPA -> true // ~ 1-23 Mbps
                    TelephonyManager.NETWORK_TYPE_UMTS -> true // ~ 400-7000 kbps
                    TelephonyManager.NETWORK_TYPE_EHRPD -> true // ~ 1-2 Mbps
                    TelephonyManager.NETWORK_TYPE_EVDO_B -> true // ~ 5 Mbps
                    TelephonyManager.NETWORK_TYPE_HSPAP -> true // ~ 10-20 Mbps
                    TelephonyManager.NETWORK_TYPE_IDEN -> false // ~25 kbps
                    TelephonyManager.NETWORK_TYPE_LTE -> true // ~ 10+ Mbps
                    TelephonyManager.NETWORK_TYPE_UNKNOWN -> false
                    else -> false
                }
            } else {
                false
            }
        }

        fun isConnectivity(): Boolean {
            return try {
                val p1 =
                        Runtime.getRuntime().exec("ping -c 1 www.google.com")
                val returnVal = p1.waitFor()
                returnVal == 0
            } catch (e: Exception) {
                // TODO Auto-generated catch block
                e.printStackTrace()
                false
                //            if (connectGoogle()) {
                //                return true;
                //            } else {
                //                return false;
                //            }
            }
        }

    }
}