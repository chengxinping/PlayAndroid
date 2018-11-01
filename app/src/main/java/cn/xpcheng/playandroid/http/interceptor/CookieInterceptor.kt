package cn.xpcheng.playandroid.http.interceptor

import cn.xpcheng.playandroid.utils.SharedPreferenceUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author ChengXinPing
 * @time   2018/11/1 15:08
 *
 */
class CookieInterceptor : Interceptor {
    private var cookies: List<String> by SharedPreferenceUtils("cookies", ArrayList<String>())

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)
        val requestUrl = request.url().toString()
        val domain = request.url().host()
        // TODO 只缓存注册 登录时的cookie
        if (!response.headers("set-cookie").isEmpty()) {
            cookies = response.headers("set-cookie")
        }
        return response
    }
}