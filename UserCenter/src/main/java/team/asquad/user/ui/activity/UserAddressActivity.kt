package team.asquad.user.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.act_usercenter_user_addresslist.*
import team.asquad.base.ui.activity.BaseMvpActivity
import team.asquad.user.R
import team.asquad.user.injection.component.DaggerUserComponent
import team.asquad.user.presenter.UserAddressPresenter
import team.asquad.user.presenter.view.UserAddressView
import team.asquad.user.ui.adapter.UserAddressAdapter

class UserAddressActivity : BaseMvpActivity<UserAddressPresenter>(),UserAddressView {
    private lateinit var mAdapter: UserAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_usercenter_user_addresslist)
        injectComponent()
        initView()
    }

    override fun onGetUserAddressResult(result: String) {
    }

    override fun onGetDefaultResult(result: Boolean) {
    }

    override fun onDeleteResult(result: Boolean) {
    }
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }

    private fun initView() {
        mUserCenterUserAddressRv.layoutManager = LinearLayoutManager(this)
        mAdapter = UserAddressAdapter(this)
        mUserCenterUserAddressRv.adapter = mAdapter
    }
}
