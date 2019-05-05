package team.asquad.base.utils

import android.util.DisplayMetrics
import android.app.Activity
import android.os.Environment
import android.os.Environment.MEDIA_MOUNTED



/**
 *   @Author ACloud
 *   @Time 2019/4/11 20:43
 *   @Explain
 *   @Version
 **/
object CommonUtils{
    /** 根据屏幕宽度与密度计算GridView显示的列数， 最少为三列，并获取Item宽度  */
    fun getImageItemWidth(activity: Activity): Int {
        val screenWidth = activity.resources.displayMetrics.widthPixels
        val densityDpi = activity.resources.displayMetrics.densityDpi
        var cols = screenWidth / densityDpi
        cols = if (cols < 3) 3 else cols
        val columnSpace = (2 * activity.resources.displayMetrics.density).toInt()
        return (screenWidth - columnSpace * (cols - 1)) / cols
    }

    /**
     * 判断SDCard是否可用
     */
    fun existSDCard(): Boolean {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
    }

    /**
     * 获取手机大小（分辨率）
     */
    fun getScreenPix(activity: Activity): DisplayMetrics {
        val displaysMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displaysMetrics)
        return displaysMetrics
    }
}