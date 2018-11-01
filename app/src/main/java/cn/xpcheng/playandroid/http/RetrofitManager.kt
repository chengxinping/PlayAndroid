package cn.xpcheng.playandroid.http

import cn.xpcheng.playandroid.App
import cn.xpcheng.playandroid.api.ApiService
import cn.xpcheng.playandroid.api.UriConstant
import cn.xpcheng.playandroid.http.interceptor.CacheInterceptor
import cn.xpcheng.playandroid.http.interceptor.CookieInterceptor
import cn.xpcheng.playandroid.http.interceptor.HeaderInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author ChengXinPing
 * @time   2018/10/31 17:17
 *
 */
object RetrofitManager {

    const val MAX_CACHE_SIZE: Long = 1024 * 1024 * 10 // 10M 的缓存大小
    const val DEFAULT_TIMEOUT: Long = 15

    val service: ApiService by lazy { getRetrofit()!!.create(ApiService::class.java) }

    private var retrofit: Retrofit? = null

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitManager::class.java) {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder().run {
                        baseUrl(UriConstant.BASE_URL)
                        client(getOkhttpClient())
                        addConverterFactory(GsonConverterFactory.create())
                        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        build()
                    }
                }
            }
        }
        return retrofit
    }


    private fun getOkhttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        //设置 请求的缓存的大小跟位置
        val cacheFile = File(App.context.cacheDir, "cache")
        val cache = Cache(cacheFile, MAX_CACHE_SIZE)
        return OkHttpClient().newBuilder().run {
            addInterceptor(HeaderInterceptor())
            addInterceptor(CacheInterceptor())
            addInterceptor(CookieInterceptor())
            addInterceptor(httpLoggingInterceptor)
            cache(cache)
            connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            build()
        }
    }
}