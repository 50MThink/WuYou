package team.asquad.base.presenter.view

/**
 *   @Author ACloud
 *   @Time 2019/3/29 20:16
 *   @Explain
 *   @Version
 **/
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text: String)
}