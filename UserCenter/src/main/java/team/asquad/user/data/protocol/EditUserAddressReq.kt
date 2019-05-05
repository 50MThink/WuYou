package team.asquad.user.data.protocol

/**
 *   @Author ACloud
 *   @Time 2019/4/3 19:06
 *   @Explain
 *   @Version
 **/
data class EditUserAddressReq(
 val id: Int,
 val UserName: String,
 val UserMobile: String,
 val UserAddress: String,
 val isDefault: Int
)