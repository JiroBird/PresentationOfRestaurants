@file:Suppress("DEPRECATION")
package com.github.jirobird.techdemo.restaurantinfo.bll

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectivityChecker {
    companion object{
        fun isNetworkConnected(context: Context): Boolean { //INFO: да-да, в новых андроидах больше 10 актуален NetworkCallback
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        }
    }
}