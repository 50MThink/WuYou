package team.asquad.user.presenter

import team.asquad.base.presenter.BasePresenter
import team.asquad.user.presenter.view.LoginView
import javax.inject.Inject

/**
 *   @Author ACloud
 *   @Time 2019/3/30 18:42
 *   @Explain
 *   @Version
 **/
class LoginPresenter @Inject constructor(): BasePresenter<LoginView>() {
    fun login(){
        mView.onLoginResult("登陆成功")
    }
}