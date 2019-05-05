package team.asquad.base.injection.component

import android.app.Activity
import android.content.Context
import dagger.Component
import team.asquad.base.injection.ActivityScope
import team.asquad.base.injection.module.ActivityModule

/**
 *   @Author ACloud
 *   @Time 2019/3/30 19:01
 *   @Explain
 *   @Version
 **/
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class),dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
}