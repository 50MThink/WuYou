package team.asquad.base.common

import android.app.Application
import team.asquad.base.injection.component.AppComponent
import team.asquad.base.injection.component.DaggerAppComponent
import team.asquad.base.injection.module.AppModule

/**
 *   @Author ACloud
 *   @Time 2019/3/30 18:57
 *   @Explain
 *   @Version
 **/
class BaseApplication: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}