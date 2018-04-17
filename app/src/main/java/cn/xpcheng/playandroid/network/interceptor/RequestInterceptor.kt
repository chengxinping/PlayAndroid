package cn.xpcheng.playandroid.network.interceptor

import okhttp3.*
import okio.Buffer
import java.io.IOException

/**
 * @author ChengXinPing
 * @time   2018/4/17 11:24
 *
 */
class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain?): Response? {
        val original = chain?.request()
        val method: String = original?.method().toString()
        val headersParams = hashMapOf<String, String>()
        headersParams["Content-Type"] = "application/json;charset=UTF-8"
        headersParams["Connection"] = "close"
        val request: Request? = when (method) {
            "POST" ->
                original?.newBuilder()
                        ?.headers(setHeaders(headersParams))
                        ?.post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),
                                bodyToString(original.body())))
                        ?.build()
            "GET" ->
                original?.newBuilder()
                        ?.headers(setHeaders(headersParams))
                        ?.build()
            else ->
                original?.newBuilder()
                        ?.headers(setHeaders(headersParams))
                        ?.build()
        }
        return chain?.proceed(request)
    }

    @Throws(IOException::class)
    fun bodyToString(requestBody: RequestBody?): String {
        val copy = requestBody
        var buffer: Buffer = Buffer()
        copy?.writeTo(buffer)
        return buffer.readUtf8()
    }

    fun setHeaders(headersParams: HashMap<String, String>): Headers {
        var builder: Headers.Builder = Headers.Builder()
        headersParams?.entries.forEach {
            builder[it.key] = it.value
        }
        return builder.build()
    }
}