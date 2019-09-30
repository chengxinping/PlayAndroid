package cn.xpcheng.playandroid.ext

import android.content.Context
import android.widget.Toast
import com.orhanobut.logger.Logger

/**
 * @author ChengXinPing
 * @time   2019/9/29 16:26
 *
 */
fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun Any.logd() {
    Logger.d(this)
}
