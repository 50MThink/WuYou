package team.asquad.base.widgets

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.graphics.drawable.Drawable
import android.graphics.Bitmap
import android.net.Uri
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.Shader
import android.graphics.BitmapShader
import android.util.Log
import team.asquad.base.R

/**
 *   @Author ACloud
 *   @Time 2019/3/3 19:52
 *   @Explain
 *   @Version
 **/
class RountImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    private  val SCALE_TYPE:ScaleType = ScaleType.CENTER_CROP

    /**
     * 使用的颜色声明
     */
    private val BITMAP_CONFIG: Bitmap.Config = Bitmap.Config.ARGB_8888
    /**
     * 位图的宽和高
     */
    private val COLORDRAWABLE_DIMENSION: Int = 2
    /**
     * 默认边界宽度
     */
    private val DEFAULT_BORDER_WIDTH: Int = 0
    /**
     * 默认边界颜色
     */
    private val DEFAULT_BORDER_COLOR:Int = Color.BLACK
    /**
     * 默认填充颜色
     */
    private val DEFAULT_FILL_COLOR : Int = Color.TRANSPARENT // 透明
    /**
     * 默认判定是否显示边界
     */
    private val DEFAULT_BORDER_OVERLAY: Boolean = false

    /*
        初始化绘制工具
        RectF: 单精度的矩形工具
     */
    private val mDrawableRect: RectF = RectF()
    private val mBorderRect: RectF = RectF()
    private val mRcBitmap: RectF = RectF()
    /*
        Matrix: 主要用于坐标映射
     */
    private val mShaderMatrix: Matrix = Matrix()
    /*
        初始化画笔
     */
    private val mBitmapPaint: Paint = Paint() // 图片画笔
    private val mBorderPaint: Paint = Paint() // 边框画笔
    private val mFillPaint: Paint = Paint() // 填充画笔
    /**
     * 初始化默认属性
     */
    private var mBorderColor: Int = DEFAULT_BORDER_COLOR
    private var mBorderWidth: Int = DEFAULT_BORDER_WIDTH
    private var mFillColor: Int = DEFAULT_FILL_COLOR

    private  var  mBitmap: Bitmap? = null
    /**
     * BitShade: 着色器，也称为颜色填充，类似ps中的印章工具，可以选择利用各种图片或者颜色来填充控件
     */
    private lateinit var mBitmapShader: BitmapShader
    /**
     * 位图宽高
     */
    private var mBitmapWidth: Int = 0
    private var mBitmapHeight: Int = 0
    /**
     * 图片半径以及边框半径
     */
    private var mDrawableRadius: Float = 0F
    private var mBorderRadius: Float = 0F
    /**
     * ColorFilter: 颜色过滤器，可以过滤或者调整图片显示出的颜色，比如调整对比度
     */
    private lateinit var mColorFilter: ColorFilter
    /**
     * 页面参数
     */
    private var mReady: Boolean = false
    private var mSetupPending : Boolean = false
    private var mBorderOverlay: Boolean = false
    private var mShape: Int = 1
    private var mRoundRadius: Float = 0F
    /**
     * 默认形状
     */
    val SHAPE_RECT = 1 // 矩形
    val SHAPE_CIRCLE = 2 // 圆形
    init {
        var typedArr: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView,defStyleAttr,0)

        mBorderWidth = typedArr.getDimensionPixelSize(R.styleable.CircleImageView_civ_border_width, DEFAULT_BORDER_COLOR)
        mBorderColor = typedArr.getColor(R.styleable.CircleImageView_civ_border_color, DEFAULT_BORDER_COLOR)
        mBorderOverlay = typedArr.getBoolean(R.styleable.CircleImageView_civ_border_overlay,DEFAULT_BORDER_OVERLAY)
        mFillColor = typedArr.getColor(R.styleable.CircleImageView_civ_fill_color,DEFAULT_FILL_COLOR)
        mRoundRadius = typedArr.getDimensionPixelSize(R.styleable.CircleImageView_civ_round_radius, DEFAULT_FILL_COLOR).toFloat()
        mShape = typedArr.getColor(R.styleable.CircleImageView_civ_shape,DEFAULT_FILL_COLOR)
        typedArr.recycle()

        super.setScaleType(SCALE_TYPE) // 返回缩放类型
        mReady = true
        if (mSetupPending){
            setup()
            mSetupPending = false
        }
    }

    /**
     *  获取缩放类型
     */
    override fun getScaleType(): ScaleType {
        return SCALE_TYPE
    }

    /**
     *  设置缩放类型
     */
    override fun setScaleType(scaleType: ImageView.ScaleType) {
        if (scaleType != SCALE_TYPE) {
            throw IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType)) // 缩放类型不支持
        }
    }

    /**
     * 设置边界
     */
    override fun setAdjustViewBounds(adjustViewBounds: Boolean) {
        if (adjustViewBounds) {
            throw IllegalArgumentException("adjustViewBounds not supported.") // 边界调整不支持
        }
    }

    /**
     *  绘制
     */
    override fun onDraw(canvas: Canvas) {
        if (mBitmap == null) { // 位图为空，不进行下一步操作
            return
        }
        if (mShape == SHAPE_RECT){
            if (mFillColor != Color.TRANSPARENT){ // 填充颜色不为透明
                canvas.drawRoundRect(mBorderRect,mRoundRadius,mRoundRadius,mFillPaint)
            }
            canvas.drawRoundRect(mBorderRect,mRoundRadius,mRoundRadius,mBitmapPaint)
            if (mBorderWidth != 0){
                canvas.drawRoundRect(mBorderRect,mRoundRadius,mRoundRadius,mBorderPaint)
            }
        }else if (mShape == SHAPE_CIRCLE){
            /*
            * 无论有无设置填充颜色都进行颜色填充
            * */
            if (mFillColor != Color.TRANSPARENT) {
                /**
                 *  绘制圆形，|参数1:圆心的x坐标 | 参数2: 圆心的y坐标 |参数3: 边框 |参数4: 绘制的画笔对象|
                 */
                canvas.drawCircle(width / 2.0f, height / 2.0f, mDrawableRadius, mFillPaint)
            }
            canvas.drawCircle(width / 2.0f, height / 2.0f, mDrawableRadius, mBitmapPaint)

            if (mBorderWidth != 0) { // 设置边框
                canvas.drawCircle(width / 2.0f, height / 2.0f, mBorderRadius, mBorderPaint)
            }

        }
    }

    /**
     * 视图大小发生变化时，调用此方法
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setup()
    }

    /**
     * 获得边界颜色
     */
    fun getBorderColor(): Int {
        return mBorderColor
    }

    /**
     * 设置边界颜色
     */
    fun setBorderColor(@ColorInt borderColor: Int) {
        if (borderColor == mBorderColor) {
            return
        }

        mBorderColor = borderColor
        mBorderPaint.color = mBorderColor
        invalidate()
    }

    /**
     * 设置颜色填充
     */
    fun setFillColor(@ColorInt fillColor: Int) {
        if (fillColor == mFillColor) {
            return
        }

        mFillColor = fillColor
        mFillPaint.color = fillColor
        invalidate()
    }

    /**
     * 设置颜色填充资源
     */
    fun setFillColorResource(@ColorRes fillColorRes: Int) {
        setFillColor(context.resources.getColor(fillColorRes))
    }

    /**
     * 获取边界宽度
     */
    fun getBorderWidth(): Int {
        return mBorderWidth
    }

    /**
     * 设置边界宽度
     */
    fun setBorderWidth(borderWidth: Int) {
        if (borderWidth == mBorderWidth) {
            return
        }

        mBorderWidth = borderWidth
        setup()
    }

    /**
     * 获得边界覆盖物
     */
    fun isBorderOverlay(): Boolean {
        return mBorderOverlay
    }

    fun setBorderOverlay(borderOverlay: Boolean) {
        if (borderOverlay == mBorderOverlay) {
            return
        }
        mBorderOverlay = borderOverlay
        setup()
    }

    /**
     * 设置位图
     */
    override fun setImageBitmap(bm: Bitmap) {
        super.setImageBitmap(bm)
        mBitmap = bm
        setup()
    }

    /**
     * 设置图片资源
     */
    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        mBitmap = getBitmapFromDrawable(drawable)
        setup()
    }

    override fun setImageResource(@DrawableRes resId: Int) {
        super.setImageResource(resId)
        mBitmap = getBitmapFromDrawable(drawable)
        setup()
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        mBitmap = if (uri != null) getBitmapFromDrawable(drawable) else null
        setup()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setup()
    }
    /**
     * 获取图片资源
     */
    private fun getBitmapFromDrawable(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }

        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        try {
            val bitmap: Bitmap
            // 实例化位图对象
            if (drawable is ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG)
            } else {
                bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, BITMAP_CONFIG)
            }
            // 设置画布
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    /**
     * 每次有参数改变时进行参数的调整
     */
    private fun setup() {
        if (!mReady) {
            mSetupPending = true
            return
        }

        if (width == 0 && height == 0) {
            return
        }

        if (mBitmap == null) {
            invalidate()
            return
        }

        mBitmapShader = BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        mBitmapPaint.isAntiAlias = true
        mBitmapPaint.shader = mBitmapShader

        mBorderPaint.style = Paint.Style.STROKE
        mBorderPaint.isAntiAlias = true // 抗锯齿
        mBorderPaint.color = mBorderColor
        mBorderPaint.strokeWidth = mBorderWidth.toFloat()

        mFillPaint.style = Paint.Style.FILL // 填充
        mFillPaint.isAntiAlias = true
        mFillPaint.color = mFillColor

        mBitmapHeight = mBitmap!!.height
        mBitmapWidth = mBitmap!!.width

        if (mShape == SHAPE_CIRCLE){
            mBorderRect.set(0F, 0F, width.toFloat(), height.toFloat()) // 绘制矩形
        }else if (mShape == SHAPE_RECT){
            mBorderRect.set(mBorderWidth.toFloat(), mBorderWidth.toFloat(), width.toFloat(), height.toFloat()) // 绘制矩形
        }
        mBorderRadius = Math.min((mBorderRect.height() - mBorderWidth) / 2.0f, (mBorderRect.width() - mBorderWidth) / 2.0f) // 求最小，也就是哪边短哪边做直径

        mDrawableRect.set(mBorderRect)
        if (!mBorderOverlay) {
            mDrawableRect.inset(mBorderWidth.toFloat(), mBorderWidth.toFloat())
        }
        mDrawableRadius = Math.min(mDrawableRect.height() / 2.0f, mDrawableRect.width() / 2.0f)

        updateShaderMatrix()
        invalidate()
    }

    /**
     * 更新着色器和图形变换工具
     */
    private fun updateShaderMatrix() {
        val scale: Float
        var dx = 0f
        var dy = 0f

        mShaderMatrix.set(null) // 把src复制到这个矩阵中，如果参数位null,则返回单位矩阵（除对角线位1，其他元素为0）

        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) { // 比较面积，使用短边来绘制
            scale = mDrawableRect.height() / mBitmapHeight.toFloat()
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f
        } else {
            scale = mDrawableRect.width() / mBitmapWidth.toFloat()
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f
        }
        Log.e("Main:5","mDrawableRect: $mDrawableRect scale: $scale mBitmapWidth: $mBitmapWidth")
        mShaderMatrix.setScale(scale, scale) // 控制 sx,sy 方向的缩放比例
        mShaderMatrix.postTranslate((dx + 0.5f).toInt() + mDrawableRect.left, (dy + 0.5f).toInt() + mDrawableRect.top) // 控制Matrix进行平移

        mBitmapShader.setLocalMatrix(mShaderMatrix) // 配置着色器矩阵
    }
}
