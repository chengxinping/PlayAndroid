package cn.xpcheng.playandroid.network.interceptor

import android.content.Context
import cn.xpcheng.common.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author ChengXinPing
 * @time   2018/4/17 16:40
 *
 */
class CacheInterceptor(context: Context) : Interceptor {
    private val context = context
    override fun intercept(chain: Interceptor.Chain?): Response? {
        var request = chain?.request()
        if (!NetworkUtils.isConnected(context)) {
            request = chain?.request()?.newBuilder()
                    ?.cacheControl(CacheControl.FORCE_CACHE)
                    ?.build()
        }
        var response = chain?.proceed(request)
        response = if (NetworkUtils.isConnected(context)) {
            val maxAge = 1 * 60
            response?.newBuilder()
                    ?.removeHeader("Pragma")
                    ?.header("Cache-Control", "public, max-age=" + maxAge)//设置缓存超时时间
                    ?.build()
        } else {
            val maxStale = 60 * 60 * 24// 无网络时，设置超时为1天
            response?.newBuilder()
                    ?.removeHeader("Pragma")
                    ?.header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    ?.build()
        }
        return response
    }
}