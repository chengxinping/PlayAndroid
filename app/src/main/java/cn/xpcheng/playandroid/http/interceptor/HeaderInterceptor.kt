package cn.xpcheng.playandroid.http.interceptor

import android.os.Build
import cn.xpcheng.playandroid.App
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.utils.SharedPreferenceUtils
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author ChengXinPing
 * @time   2018/10/31 17:26
 *
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Content-type", "application/json; charset=utf-8")
        builder.addHeader("User-Agent", App.context.getString(R.string.app_name) + "/" + " V" + App.context.packageManager.getPackageInfo(App.context.packageName, 0).versionName + "(" + Build.MODEL + ";" + Build.VERSION.RELEASE + ")")

        //TODO: 部分接口需要将缓存的cookie添加到header  目前是全部加到header
        val cookies: List<String> by SharedPreferenceUtils("cookies", ArrayList<String>())
        if (cookies.size > 0) {
            Observable.fromIterable(cookies)
                    .subscribe({ cookie ->
                        builder.addHeader("Cookie", cookie)
                    })
        }
        return chain.proceed(builder.build())
    }

}