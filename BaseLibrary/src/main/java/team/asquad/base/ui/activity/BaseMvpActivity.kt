package team.asquad.base.ui.activity

import android.os.Bundle
import team.asquad.base.common.BaseApplication
import team.asquad.base.ext.toast
import team.asquad.base.injection.component.ActivityComponent
import team.asquad.base.injection.component.DaggerActivityComponent
import team.asquad.base.injection.module.ActivityModule
import team.asquad.base.presenter.BasePresenter
import team.asquad.base.presenter.view.BaseView
import javax.inject.Inject

/**
 *   @Author ACloud
 *   @Time 2019/3/30 18:36
 *   @Explain
 *   @Version
 **/
abstract class BaseMvpActivity<T : BasePresenter<out BaseView>>: BaseActivity(), BaseView {
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

    override fun hideLoading() {
    }

    override fun showLoading() {
    }

    override fun onError(text: String) {
        toast(text).show()
    }
}