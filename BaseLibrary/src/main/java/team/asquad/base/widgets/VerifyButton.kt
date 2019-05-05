package team.asquad.base.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.os.Handler
import team.asquad.base.R

/**
 *   @Author ACloud
 *   @Time 2019/4/10 18:59
 *   @Explain 获取验证码按钮，带倒计时
 *   @Version
 **/
class VerifyButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Button(context, attrs, defStyleAttr) {
    private val mHandler: Handler
    private var mCount = 60
    private var mOnVerifyBtnClick: OnVerifyBtnClick? = null

    init {
        this.text = "获取验证码"
        // 实例化Handler
        mHandler = Handler()
    }

    /*
        倒计时，并处理点击事件
     */
    fun requestSendVerifyNumber() {
        // 点击时调用当前方法，调用postDelayed
        mHandler.postDelayed(countDown, 0)
        // 自定义一个点击事件
        if (mOnVerifyBtnClick != null) {
            mOnVerifyBtnClick!!.onClick()
        }
    }

    /*
        倒计时
     */
    private val countDown = object : Runnable {
        override fun run() {
            // 获得当前类对象
            this@VerifyButton.text = mCount.toString() + "s "
            this@VerifyButton.setBackgroundColor(resources.getColor(R.color.common_disable))
            this@VerifyButton.setTextColor(resources.getColor(R.color.common_white))
            this@VerifyButton.isEnabled = false
            // 当计数 mCount 不大于0时，就会调用resetCounter()
            if (mCount > 0) {
                // 延迟1000毫秒后执行当前匿名内部类
                mHandler.postDelayed(this, 1000)
            } else {
                resetCounter()
            }
            mCount--
        }
    }

    fun removeRunable() {
        mHandler.removeCallbacks(countDown)
    }

    /*
        恢复到初始状态 ；vararg 相当于java的 ...
     */
    fun resetCounter(vararg text: String) {
        this.isEnabled = true
        if (text.isNotEmpty() && "" != text[0]) {
            this.text = text[0]
        } else {
            this.text = "重获验证码"
        }
        this.setBackgroundColor(resources.getColor(R.color.transparent))
        this.setTextColor(resources.getColor(R.color.common_blue))
        mCount = 60
    }

    /*
        点击事件接口
     */
    interface OnVerifyBtnClick {
        fun onClick()
    }

    /*
        是否被点击
     */
    fun isUnClick(): Boolean{
        return mCount != 60
    }
    fun setOnVerifyBtnClick(onVerifyBtnClick: OnVerifyBtnClick) {
        this.mOnVerifyBtnClick = onVerifyBtnClick
    }
}