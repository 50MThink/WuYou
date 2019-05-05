package team.asquad.user.ui.activity

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.fra_usercenter_me.*
import team.asquad.base.ext.onClick
import team.asquad.base.ext.toast
import team.asquad.base.ui.activity.BaseMvpActivity
import team.asquad.user.R
import team.asquad.user.injection.component.DaggerUserComponent
import team.asquad.user.presenter.LoginPresenter
import team.asquad.user.presenter.view.LoginView

class LoginActivity : BaseMvpActivity<LoginPresenter>(),LoginView  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fra_usercenter_me)
        injectComponent()
        mPresenter.login()
        initView()
    }



    private fun initView() {
        mMeLocationTv.onClick {
            val intent: Intent = Intent(this@LoginActivity,UserAddressActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.enter_in_from_right,R.anim.enter_to_left)
        }
    }

    override fun onLoginResult(result: String) {
        toast(result).show()
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }
}
