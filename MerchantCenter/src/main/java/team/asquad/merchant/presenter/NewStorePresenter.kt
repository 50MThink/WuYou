package team.asquad.merchant.presenter

import team.asquad.base.presenter.BasePresenter
import team.asquad.merchant.presenter.view.NewStoreView
import javax.inject.Inject

/**
 *   @Author ACloud
 *   @Time 2019/5/5 9:28
 *   @Explain
 *   @Version
 **/
class NewStorePresenter @Inject constructor(): BasePresenter<NewStoreView>() {
    fun newStore(result: String){
        mView.onNewStore("新建")
    }
}