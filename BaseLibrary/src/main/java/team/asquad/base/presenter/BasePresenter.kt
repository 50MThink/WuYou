package team.asquad.base.presenter

import android.content.Context
import team.asquad.base.presenter.view.BaseView
import team.asquad.base.utils.NetWorkUtils
import javax.inject.Inject

/**
 *   @Author ACloud
 *   @Time 2019/3/29 20:15
 *   @Explain
 *   @Version
 **/
open class BasePresenter<T: BaseView> {
    lateinit var mView: T
    @Inject
    lateinit var context: Context
    fun checkNetWork(): Boolean{

        if (NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用")
        return false

    }
}