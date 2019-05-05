package team.asquad.wuyou

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import team.asquad.base.ext.onClick
import team.asquad.base.ui.activity.BaseActivity
import team.asquad.base.utils.statusbar.StatusBarUtil
import team.asquad.wuyou.wuyou.R

class MainActivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initImmersionBar()
    }
    private fun initImmersionBar() {
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initView() {
        mMainSellBtn.onClick {
            val mStatusBar : StatusBarUtil = StatusBarUtil()
            mStatusBar.setAutoStatusBarColor(this)
        }
        mMainBuyBtn.onClick {

        }
    }


    }
