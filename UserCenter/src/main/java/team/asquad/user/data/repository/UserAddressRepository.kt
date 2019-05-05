package team.asquad.user.data.repository

import team.asquad.user.data.protocol.UserAddress
import javax.inject.Inject

/**
 *   @Author ACloud
 *   @Time 2019/4/2 21:13
 *   @Explain
 *   @Version
 **/
class UserAddressRepository {
    /*
        添加用户地址
     */
    fun addUserAddress(UserName: String, UserMobile: String, shipAddress: String){

    }
    /*
        删除用户地址
     */
    fun deleteUserAddress(id: Int){

    }
    /*
        修改用户地址
     */
    fun editShipAddress(address: UserAddress){}
    /*
        获取收货地址列表
     */
    fun getUserAddressList(){}
}