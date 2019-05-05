package team.asquad.base.data.protocol

/**
 *   @Author ACloud
 *   @Time 2019/4/2 21:10
 *   @Explain
 *   @Version
 **/
data class BaseResp<out T>(val status: Int, val message: String, val data: T) {
}