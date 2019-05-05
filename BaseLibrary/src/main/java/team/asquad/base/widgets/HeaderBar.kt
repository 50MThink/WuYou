package team.asquad.base.widgets

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.common_header_bar.view.*
import team.asquad.base.R

/**
 *   @Author ACloud
 *   @Time 2019/4/3 21:04
 *   @Explain
 *   @Version
 **/
class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    // 显示与隐藏
    private var isShowBack: Boolean = true
    private var isRightTitle: Boolean = false
    private var isShowRightBtn: Boolean = false
    // 标题
    private var titleText: String? = null
    // 右边标题文字
    private var rightTitle: String?  = null
    // 右边按钮
    private var rightBtn: Int? = null

    init {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)
        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack,true)
        isShowRightBtn = typedArray.getBoolean(R.styleable.HeaderBar_isShowRightBtn,false)
        isRightTitle = typedArray.getBoolean(R.styleable.HeaderBar_isShowRightText,false)
        titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
        rightTitle = typedArray.getString(R.styleable.HeaderBar_rightText)
        rightBtn = typedArray.getResourceId(R.styleable.HeaderBar_rightBtn,0)
        initView()
        typedArray.recycle()
    }

    private fun initView(){
        View.inflate(context,R.layout.common_header_bar,this)
        mHeadNavBackIv.visibility = if (isShowBack) View.VISIBLE else View.GONE // 返回按钮显示控制
        mHeadNavRightBtnIv.visibility = if (isShowRightBtn) View.VISIBLE else View.GONE
        titleText?.let {
            mHeadNavTitleTv.text = it
//            mHeadNavTitleTv.paint.isFakeBoldText = true // 加粗
        }
        rightBtn?.let {
            mHeadNavRightBtnIv.setImageResource(it)
        }
        rightTitle?.let {
            if (isShowRightBtn){
                mHeadNavRightTitleTv.gravity = right
            }
            mHeadNavRightTitleTv.text = it
        }
        mHeadNavBackIv.setOnClickListener {
            if (context is Activity){
                (context as Activity).finish()
                (context as Activity).overridePendingTransition(R.anim.exit_slide_left,R.anim.exit_slide_right)
            }
        }
    }

    /**
     *  开放各个布局
     */
    fun getBackView(): ImageView{
        return mHeadNavBackIv
    }
    fun getTitleView(): TextView{
        return mHeadNavTitleTv
    }
    fun getRightTitleView(): TextView{
        return mHeadNavRightTitleTv
    }
    fun getRightBtnView(): ImageView{
        return mHeadNavRightBtnIv
    }

}