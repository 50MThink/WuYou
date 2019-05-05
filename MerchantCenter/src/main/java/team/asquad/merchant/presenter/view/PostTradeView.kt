package team.asquad.merchant.presenter.view

import team.asquad.base.presenter.view.BaseView

/**
 *   @Author ACloud
 *   @Time 2019/5/5 18:15
 *   @Explain
 *   @Version
 **/
interface PostTradeView :BaseView {
    fun onPostTrade(result: String)
}