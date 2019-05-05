package team.asquad.user.presenter.view

import team.asquad.base.presenter.view.BaseView

/**
 *   @Author ACloud
 *   @Time 2019/3/30 18:42
 *   @Explain
 *   @Version
 **/
interface LoginView: BaseView {
    fun onLoginResult(result: String)
}