package team.asquad.base.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import team.asquad.base.injection.ActivityScope

/**
 *   @Author ACloud
 *   @Time 2019/3/30 18:58
 *   @Explain
 *   @Version
 **/
@Module
class ActivityModule(private val  activity: Activity) {
    // module 创建所有与BaseApplication由所依赖并且由加@Inject标注的实例对象
    @Provides
    @ActivityScope
    fun provideActivity(): Activity {
        return this.activity
    }
}