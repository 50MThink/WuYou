package team.asquad.merchant.injection.component

import dagger.Component
import team.asquad.base.injection.PerComponentScope
import team.asquad.base.injection.component.ActivityComponent
import team.asquad.merchant.ui.activity.NewStoreActivity


/**
 *   @Author ACloud
 *   @Time 2019/5/5 9:33
 *   @Explain
 *   @Version
 **/
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class))
interface MerchantComponent {
    fun inject(activity: NewStoreActivity)
}