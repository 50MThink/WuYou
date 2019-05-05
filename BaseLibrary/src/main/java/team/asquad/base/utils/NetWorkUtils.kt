package team.asquad.base.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 *   @Author ACloud
 *   @Time 2019/3/30 18:40
 *   @Explain
 *   @Version
 **/
object NetWorkUtils {
    fun isNetWorkAvailable(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val netWorkInfo = connectivityManager.activeNetworkInfo
        return netWorkInfo != null && netWorkInfo.isConnected
    }

    /*
        检测wifi是否连接
     */
    fun isWifiConnected(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val netWorkInfo = cm.activeNetworkInfo
        return netWorkInfo != null && netWorkInfo.type == ConnectivityManager.TYPE_WIFI
    }

    /*
         检测数据网络是否连接
     */
    fun isDataNetWorkConnected(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val netWorkInfo = cm.activeNetworkInfo
        return netWorkInfo != null && netWorkInfo.type == ConnectivityManager.TYPE_MOBILE
    }
}