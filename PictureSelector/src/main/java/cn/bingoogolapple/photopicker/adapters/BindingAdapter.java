package cn.bingoogolapple.photopicker.adapters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * BindingAdapter is applied to methods that are used to manipulate how values with expressions
 * are set to views. The simplest example is to have a public static method that takes the view
 * and the value to set:
 * <p><pre>
 *<code>@BindingAdapter("android:bufferType")
 * public static void setBufferType(TextView view, TextView.BufferType bufferType) {
 *     view.setText(view.getText(), bufferType);
 * }</code></pre>
 * In the above example, when android:bufferType is used on a TextView, the method
 * setBufferType is called.
 * <p>
 * It is also possible to take previously set values, if the old values are listed first:
 * <p><pre>
 *<code>@BindingAdapter("android:onLayoutChange")
 * public static void setOnLayoutChangeListener(View view, View.OnLayoutChangeListener oldValue,
 *                                              View.OnLayoutChangeListener newValue) {
 *     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
 *         if (oldValue != null) {
 *             view.removeOnLayoutChangeListener(oldValue);
 *         }
 *         if (newValue != null) {
 *             view.addOnLayoutChangeListener(newValue);
 *         }
 *     }
 * }</code></pre>
 * When a binding adapter may also take multiple attributes, it will only be called when all
 * attributes associated with the binding adapter have binding expressions associated with them.
 * This is useful when there are unusual interactions between attributes. For example:
 * <p><pre>
 *<code>@BindingAdapter({"android:onClick", "android:clickable"})
 * public static void setOnClick(View view, View.OnClickListener clickListener,
 *                               boolean clickable) {
 *     view.setOnClickListener(clickListener);
 *     view.setClickable(clickable);
 * }</code></pre>
 * The order of the parameters must match the order of the attributes in values in the
 * BindingAdapter.
 * <p>
 * A binding adapter may optionally take a class extending DataBindingComponent as the first
 * parameter as well. If it does, it will be passed the value passed in during binding, either
 * directly in the inflate method or indirectly, using the value from
 * {@link DataBindingUtil#getDefaultComponent()}.
 * <p>
 * If a binding adapter is an instance method, the generated DataBindingComponent will have
 * a getter to retrieve an instance of the BindingAdapter's class to use to call the method.
 */
@Target(ElementType.METHOD)
public @interface BindingAdapter {

    /**
     * @return The attributes associated with this binding adapter.
     */
    String[] value();

    /**
     * Whether every attribute must be assigned a binding expression or if some
     * can be absent. When this is false, the BindingAdapter will be called
     * when at least one associated attribute has a binding expression. The attributes
     * for which there was no binding expression (even a normal XML value) will
     * cause the associated parameter receive the Java default value. Care must be
     * taken to ensure that a default value is not confused with a valid XML value.
     *
     * @return whether or not every attribute must be assigned a binding expression. The default
     *         value is true.
     */
    boolean requireAll() default true;
}
