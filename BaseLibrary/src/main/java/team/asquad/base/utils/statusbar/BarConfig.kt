package team.asquad.base.utils.statusbar

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.TypedValue

/**
 * The type Bar config.
 *
 * @author geyifeng
 * @date 2017 /5/11
 */
class BarConfig
/**
 * Instantiates a new Bar config.
 *
 * @param activity the activity
 */
(activity: Activity) {

    /**
     * Get the height of the system status bar.
     *
     * @return The height of the status bar (in pixels).
     */
    val statusBarHeight: Int
    /**
     * Get the height of the action bar.
     *
     * @return The height of the action bar (in pixels).
     */
    val actionBarHeight: Int
    private val mHasNavigationBar: Boolean
    /**
     * Get the height of the system navigation bar.
     *
     * @return The height of the navigation bar (in pixels). If the device does not have soft navigation keys, this will always return 0.
     */
    val navigationBarHeight: Int
    /**
     * Get the width of the system navigation bar when it is placed vertically on the screen.
     *
     * @return The width of the navigation bar (in pixels). If the device does not have soft navigation keys, this will always return 0.
     */
    val navigationBarWidth: Int
    private val mInPortrait: Boolean
    private val mSmallestWidthDp: Float
    private val mHasNotchScreen: Boolean

    /**
     * Should a navigation bar appear at the bottom of the screen in the current
     * device configuration? A navigation bar may appear on the right side of
     * the screen in certain configurations.
     *
     * @return True if navigation should appear at the bottom of the screen, False otherwise.
     */
    val isNavigationAtBottom: Boolean
        get() = mSmallestWidthDp >= 600 || mInPortrait


    init {
        val res = activity.resources
        mInPortrait = res.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        mSmallestWidthDp = getSmallestWidthDp(activity)
        statusBarHeight = getInternalDimensionSize(activity, STATUS_BAR_HEIGHT_RES_NAME)
        actionBarHeight = getActionBarHeight(activity)
        navigationBarHeight = getNavigationBarHeight(activity)
        navigationBarWidth = getNavigationBarWidth(activity)
        mHasNavigationBar = navigationBarHeight > 0
        mHasNotchScreen = NotchUtils.hasNotchScreen(activity)
    }

    @TargetApi(14)
    private fun getActionBarHeight(context: Context): Int {
        var result = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            val tv = TypedValue()
            context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)
            result = TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        }
        return result
    }

    @TargetApi(14)
    private fun getNavigationBarHeight(context: Context): Int {
        val result = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (hasNavBar(context as Activity)) {
                val key: String
                if (mInPortrait) {
                    key = NAV_BAR_HEIGHT_RES_NAME
                } else {
                    key = NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME
                }
                return getInternalDimensionSize(context, key)
            }
        }
        return result
    }

    @TargetApi(14)
    private fun getNavigationBarWidth(context: Context): Int {
        val result = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (hasNavBar(context as Activity)) {
                return getInternalDimensionSize(context, NAV_BAR_WIDTH_RES_NAME)
            }
        }
        return result
    }

    @TargetApi(14)
    private fun hasNavBar(activity: Activity): Boolean {
        //判断小米手机是否开启了全面屏,开启了，直接返回false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (Settings.Global.getInt(activity.contentResolver, MIUI_FORCE_FSG_NAV_BAR, 0) != 0) {
                return false
            }
        }
        //其他手机根据屏幕真实高度与显示高度是否相同来判断
        val windowManager = activity.windowManager
        val d = windowManager.defaultDisplay

        val realDisplayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics)
        }

        val realHeight = realDisplayMetrics.heightPixels
        val realWidth = realDisplayMetrics.widthPixels

        val displayMetrics = DisplayMetrics()
        d.getMetrics(displayMetrics)

        val displayHeight = displayMetrics.heightPixels
        val displayWidth = displayMetrics.widthPixels

        return realWidth - displayWidth > 0 || realHeight - displayHeight > 0
    }

    private fun getInternalDimensionSize(context: Context, key: String): Int {
        val result = 0
        try {
            val resourceId = Resources.getSystem().getIdentifier(key, "dimen", "android")
            if (resourceId > 0) {
                val sizeOne = context.resources.getDimensionPixelSize(resourceId)
                val sizeTwo = Resources.getSystem().getDimensionPixelSize(resourceId)

                if (sizeTwo >= sizeOne) {
                    return sizeTwo
                } else {
                    val densityOne = context.resources.displayMetrics.density
                    val densityTwo = Resources.getSystem().displayMetrics.density
                    return Math.round(sizeOne * densityTwo / densityOne)
                }
            }
        } catch (ignored: Resources.NotFoundException) {
            return 0
        }

        return result
    }

    @SuppressLint("NewApi")
    private fun getSmallestWidthDp(activity: Activity): Float {
        val metrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.windowManager.defaultDisplay.getRealMetrics(metrics)
        } else {
            activity.windowManager.defaultDisplay.getMetrics(metrics)
        }
        val widthDp = metrics.widthPixels / metrics.density
        val heightDp = metrics.heightPixels / metrics.density
        return Math.min(widthDp, heightDp)
    }

    /**
     * Does this device have a system navigation bar?
     *
     * @return True if this device uses soft key navigation, False otherwise.
     */
    fun hasNavigationBar(): Boolean {
        return mHasNavigationBar
    }

    /**
     * Has notch screen boolean.
     *
     * @return the boolean
     */
    fun hasNotchScreen(): Boolean {
        return mHasNotchScreen
    }

    companion object {

        private val STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height"
        private val NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height"
        private val NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape"
        private val NAV_BAR_WIDTH_RES_NAME = "navigation_bar_width"
        private val MIUI_FORCE_FSG_NAV_BAR = "force_fsg_nav_bar"
    }

}