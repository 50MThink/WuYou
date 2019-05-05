package team.asquad.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME
/**
 * @创建者 xia
 * @创建时间 2018/12/30 16:45
 * @描述
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentScope