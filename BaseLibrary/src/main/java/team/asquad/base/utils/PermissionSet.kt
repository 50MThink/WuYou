package team.asquad.base.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog

/**
 *   @Author ACloud
 *   @Time 2019/4/16 20:31
 *   @Explain
 *   @Version
 **/
object PermissionSet{
    @SuppressLint("ObsoleteSdkInt")
    fun showDialog(context: Context) {
        //创建对话框创建器
        val builder = AlertDialog.Builder(context)
        //设置对话框显示小图标
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        //设置标题
        builder.setTitle("权限申请")
        //设置正文
        builder.setMessage("在设置-应用-无忧-权限 中开启相机、存储权限，才能正常使用拍照或图片选择功能")

        //添加确定按钮点击事件
        builder.setPositiveButton("去设置") { dialog, which ->
            //点击完确定后，触发这个事件
            val localIntent: Intent = Intent()
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (Build.VERSION.SDK_INT >= 9){
                localIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                localIntent.data = Uri.fromParts("package",context.packageName,null)
            }else if(Build.VERSION.SDK_INT <= 8){
                localIntent.action = Intent.ACTION_VIEW
                localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails")
                localIntent.putExtra("com.android.settings.ApplicationPkgName", context.packageName)
            }
            context.startActivity(localIntent)
        }

        //添加取消按钮点击事件
        builder.setNegativeButton("取消") { dialog, which -> }

        //使用构建器创建出对话框对象
        val dialog = builder.create()
        dialog.show()//显示对话框
    }


}