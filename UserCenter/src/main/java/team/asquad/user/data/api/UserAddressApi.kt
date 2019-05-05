package team.asquad.user.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import team.asquad.user.data.protocol.AddUserAddressReq

/**
 *   @Author ACloud
 *   @Time 2019/4/2 20:48
 *   @Explain
 *   @Version
 **/
interface UserAddressApi {
    /*
        添加用户地址
     */
    @POST("userAddress/add")
    fun addUserAddress(@Body req: AddUserAddressReq)
}