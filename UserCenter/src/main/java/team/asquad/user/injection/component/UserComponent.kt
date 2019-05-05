package team.asquad.user.injection.component

import dagger.Component
import team.asquad.base.injection.ActivityScope
import team.asquad.base.injection.PerComponentScope
import team.asquad.base.injection.component.ActivityComponent
import team.asquad.user.presenter.LoginPresenter
import team.asquad.user.ui.activity.LoginActivity
import team.asquad.user.ui.activity.UserAddressActivity

/**
 *   @Author ACloud
 *   @Time 2019/3/30 19:53
 *   @Explain
 *   @Version
 **/
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface UserComponent{
    fun inject(activity: LoginActivity)
    fun inject(activity: UserAddressActivity)
}