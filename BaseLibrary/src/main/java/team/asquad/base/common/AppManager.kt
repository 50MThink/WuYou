package team.asquad.base.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 *   @Author ACloud
 *   @Time 2019/3/24 21:32
 *   @Explain
 *   @Version
 **/
class AppManager  private constructor(){

    private val activityStack: Stack<Activity> = Stack()
    // 定义一个伴生对象
    companion object {
        // 实现一个AppManager对象引用的延迟加载，在调用时就会创建该对象的实例并且会于当前类一直存在于系统中
        val instance:AppManager by lazy { AppManager() }
    }
    /*
        入栈
     */
    fun addActivity(activity: Activity){
        // 将一个activity 对象添加入栈中
        activityStack.add(activity)
    }

    /*
        出栈
     */
    fun finishActivity(activity: Activity){
        // 将传入的activity关闭掉，再清除栈中当前的这个已关闭的activity
        activity.finish()
        activityStack.remove(activity)
    }
    /*
        清除多个栈
     */
    fun finishMoreActivity(vararg activity: Activity){
        for (a in activity){
            a.finish()
            activityStack.remove(a)
        }
    }
    /*
        获取栈顶
     */
    fun currentaActivity(): Activity {
        // 得到栈顶的第一个元素
        return activityStack.lastElement()
    }
    /*
        清除栈
     */
    fun finishAllActivity(){
        // 循环迭代取出栈中的每一个元素，一一调用每个元素的finish()方法
        for (activity in activityStack){
            activity.finish()
        }
        // 清除栈中所有的元素
        activityStack.clear()
    }

    /*
        退出应用程序
     */
    fun exitApp(context: Context){
        // 关闭引用程序的所有页面
        finishAllActivity()
        // 获取系统的ACTIVITY_SERCICE服务，用来管理应用程序的系统状态
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        // 传入的参数是通过当前方法传入的context得到的包名，杀死当前context后台的进程
        activityManager.killBackgroundProcesses(context.packageName)
        // 终止当前虚拟机
        System.exit(0)
    }
}