package team.asquad.merchant.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import team.asquad.base.ui.activity.BaseTakePrctureActivity
import team.asquad.merchant.R
import team.asquad.merchant.injection.component.DaggerMerchantComponent
import team.asquad.merchant.presenter.PostTradePresenter
import team.asquad.merchant.presenter.view.PostTradeView

class PostTradeActivity :BaseTakePrctureActivity<PostTradePresenter>(),PostTradeView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_vc_post_trade)
    }

    override fun onPostTrade(result: String) {

    }

    override fun injectComponent() {
        DaggerMerchantComponent.builder().activityComponent(activityComponent).build().inject(this)
    }
}
