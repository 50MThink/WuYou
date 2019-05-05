package team.asquad.base.ext

import android.app.Activity
import android.content.Context
import android.view.View

/**
 *   @Author ACloud
 *   @Time 2019/3/30 18:54
 *   @Explain
 *   @Version
 **/
/*
    扩展点击事件
 */
fun View.onClick(listener: View.OnClickListener): View {
    setOnClickListener(listener)
    return this
}

/*
    扩展点击事件，参数为方法
 */
fun View.onClick(method:() -> Unit): View {
    setOnClickListener { method() }
    return this
}
