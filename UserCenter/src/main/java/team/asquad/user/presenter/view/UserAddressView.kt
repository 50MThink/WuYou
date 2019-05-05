package team.asquad.user.presenter.view

import team.asquad.base.presenter.view.BaseView

/**
 *   @Author ACloud
 *   @Time 2019/4/7 10:12
 *   @Explain
 *   @Version
 **/
interface UserAddressView: BaseView {
    // 获取用户地址列表回调
    fun onGetUserAddressResult(result: String)
    // 设置默认收货地址回调
    fun onGetDefaultResult(result: Boolean)
    // 删除用户地址回调
    fun onDeleteResult(result: Boolean)

}