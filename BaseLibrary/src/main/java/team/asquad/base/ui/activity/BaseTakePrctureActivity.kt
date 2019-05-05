package team.asquad.base.ui.activity

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.aihook.alertview.library.AlertView
import com.aihook.alertview.library.OnItemClickListener
import team.asquad.base.common.BaseApplication
import team.asquad.base.injection.component.ActivityComponent
import team.asquad.base.injection.component.DaggerActivityComponent
import team.asquad.base.injection.module.ActivityModule
import team.asquad.base.presenter.BasePresenter
import team.asquad.base.presenter.view.BaseView
import javax.inject.Inject

/**
 *   @Author ACloud
 *   @Time 2019/4/17 19:52
 *   @Explain
 *   @Version
 **/
abstract class BaseTakePrctureActivity<T: BasePresenter<out BaseView>> : BaseActivity(),BaseView {
    @Inject
    lateinit var mPresenter: T
    lateinit var activityComponent: ActivityComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
    }
    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent
                .builder()
                .appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }
    abstract fun injectComponent()
    protected fun showAlertView() {
        AlertView("选择图片","","取消",null, arrayOf("拍照","相册"),this,
                AlertView.Style.ActionSheet,object : OnItemClickListener {
            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun onItemClick(o: Any?, position: Int) {
                when(position){
                    0 ->{
                    }
                    1 -> {
                    }

                }
            }
        }).show()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError(text: String) {

    }
}
