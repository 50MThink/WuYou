package team.asquad.base.injection.component

import android.content.Context
import dagger.Component
import team.asquad.base.injection.ActivityScope
import team.asquad.base.injection.module.ActivityModule
import team.asquad.base.injection.module.AppModule
import javax.inject.Singleton

/**
 *   @Author ACloud
 *   @Time 2019/3/30 19:00
 *   @Explain
 *   @Version
 **/
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent{
    fun context(): Context
}