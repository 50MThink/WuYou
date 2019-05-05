package team.asquad.base.injection
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME
/**
 * @创建者 xia
 * @创建时间 2018/12/30 10:48
 * @描述 这是一个@Singleton 标注的自定义标注
 *
 * Identifies a type that the injector only instantiates once. Not inherited.
 *
 * @see javax.inject.Scope @Scope
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope