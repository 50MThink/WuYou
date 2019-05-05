package team.asquad.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.asquad.base.common.BaseApplication
import team.asquad.base.injection.component.ActivityComponent
import team.asquad.base.injection.component.DaggerActivityComponent
import team.asquad.base.injection.module.ActivityModule
import team.asquad.base.presenter.BasePresenter
import team.asquad.base.presenter.view.BaseView
import javax.inject.Inject

/**
 *   @Author ACloud
 *   @Time 2019/4/1 19:34
 *   @Explain
 *   @Version
 **/
abstract class BaseMvpFragment<T: BasePresenter<out BaseView>>: BaseFragement(),BaseView {
    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initActivityInjection()
        injectComponent()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((activity.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity))
                .build()
    }

    abstract fun injectComponent()

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text: String) {

    }
}