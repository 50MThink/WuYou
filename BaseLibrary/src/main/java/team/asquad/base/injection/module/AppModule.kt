package team.asquad.base.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import team.asquad.base.common.BaseApplication
import javax.inject.Singleton

/**
 *   @Author ACloud
 *   @Time 2019/3/30 18:54
 *   @Explain
 *   @Version
 **/
@Module
class AppModule(private val  context: BaseApplication) {
    // @Singleton 标注的后就可以使后台知道Component可以传入的是哪个module
    @Singleton
    // module 创建所有与BaseApplication由所依赖并且由加@Inject标注的实例对象
    @Provides
    fun providesContext(): Context {
        return this.context
    }
}