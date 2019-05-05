package team.asquad.merchant.presenter

import team.asquad.base.presenter.BasePresenter
import team.asquad.merchant.presenter.view.PostTradeView
import javax.inject.Inject

/**
 *   @Author ACloud
 *   @Time 2019/5/5 18:15
 *   @Explain
 *   @Version
 **/
class PostTradePresenter @Inject constructor(): BasePresenter<PostTradeView>() {
    fun postTrade(result: String){
        mView.onPostTrade("发布")
    }
}