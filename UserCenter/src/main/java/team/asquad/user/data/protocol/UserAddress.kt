package team.asquad.user.data.protocol

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *   @Author ACloud
 *   @Time 2019/4/2 21:21
 *   @Explain
 *   @Version
 **/
@Parcelize
data class UserAddress(
        val id: Int,
        val userName: String,
        val address: String,
        val default: Int
): Parcelable {
}