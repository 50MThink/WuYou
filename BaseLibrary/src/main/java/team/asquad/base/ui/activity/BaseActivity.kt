package team.asquad.base.ui.activity

import android.app.Application
import android.os.Bundle
import android.util.Log
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import team.asquad.base.common.AppManager
import team.asquad.base.utils.PermissionSet
import team.asquad.base.utils.statusbar.StatusBarUtil
import team.asquad.base.utils.takephoto.LQRPhotoSelectUtils

/**
 *   @Author ACloud
 *   @Time 2019/3/30 18:32
 *   @Explain
 *   @Version
 **/
open class BaseActivity: RxAppCompatActivity(){
    val mStatusBarUtil: StatusBarUtil = StatusBarUtil()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
        initStatusBar()
    }

    companion object {
        val app: Application?

        init {
            var app: Application? = null
            try {
                app = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null) as Application
                if (app == null)
                    throw IllegalStateException("Static initialization of Applications must be on main thread.")
            } catch (e: Exception) {
                Log.e(BaseActivity::class.java.simpleName, "Failed to get current application from AppGlobals." + e.message)
                try {
                    app = Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null) as Application
                } catch (ex: Exception) {
                    Log.e(BaseActivity::class.java.simpleName, "Failed to get current application from ActivityThread." + e.message)
                }

            } finally {
                this.app = app
            }
        }
    }
    fun getApp(): Application? {
        return app
    }
    private fun initStatusBar() {
        mStatusBarUtil.setAutoStatusBarColor(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    override fun finish() {
        super.finish()
    }

}