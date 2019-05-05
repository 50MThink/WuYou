package team.asquad.merchant.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.act_vc_new_store.*
import team.asquad.base.ext.onClick
import team.asquad.base.ext.toast
import team.asquad.base.ui.activity.BaseTakePrctureActivity
import team.asquad.merchant.R
import team.asquad.merchant.injection.component.DaggerMerchantComponent
import team.asquad.merchant.presenter.NewStorePresenter
import team.asquad.merchant.presenter.view.NewStoreView

class NewStoreActivity : BaseTakePrctureActivity<NewStorePresenter>(),NewStoreView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_vc_new_store)
        injectComponent()
        initView()
        initClick()
    }

    private fun initView() {
    }

    private fun initClick() {
        mGetVerifyCodeBtn.onClick {
            mGetVerifyCodeBtn.requestSendVerifyNumber()
        }
    }

    override fun onNewStore(result: String) {
        toast(result).show()
    }

    override fun injectComponent() {
        DaggerMerchantComponent.builder().activityComponent(activityComponent).build().inject(this)
        mPresenter.mView = this
    }
}
