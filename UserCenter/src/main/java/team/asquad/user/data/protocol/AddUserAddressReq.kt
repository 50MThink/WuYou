package team.asquad.user.data.protocol

/**
 *   @Author ACloud
 *   @Time 2019/4/3 19:03
 *   @Explain
 *   @Version
 **/
data class AddUserAddressReq(
        val UserName: String,
        val UserMobile: String,
        val UserAddress: String
) {
}