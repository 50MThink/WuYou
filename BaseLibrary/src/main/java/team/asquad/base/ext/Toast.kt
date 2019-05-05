package team.asquad.base.ext

import android.content.Context
import android.widget.Toast

/**
 *   @Author ACloud
 *   @Time 2019/3/30 18:23
 *   @Explain
 *   @Version
 **/
fun Context.toast(sequence: CharSequence) = Toast.makeText(this,sequence,Toast.LENGTH_SHORT)