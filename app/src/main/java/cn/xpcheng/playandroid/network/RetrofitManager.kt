package cn.xpcheng.playandroid.network

import cn.xpcheng.playandroid.MyApplication
import cn.xpcheng.playandroid.api.ApiService
import cn.xpcheng.playandroid.api.UriConstant
import cn.xpcheng.playandroid.network.interceptor.CacheInterceptor
import cn.xpcheng.playandroid.network.interceptor.RequestInterceptor
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
 * @time   2018/4/17 20:47
 *
 */


object RetrofitManager {
    private var client: OkHttpClient? = null
    private var retrofit: Retrofit? = null


    val service: ApiService by lazy { getRetrofit()!!.create(ApiService::class.java) }

    private fun getRetrofit(): Retrofit? {
        retrofit ?: let {
            synchronized(RetrofitManager::class.java) {
                retrofit ?: let {
                    //设置一个日志过滤器
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    //可以设置请求过滤的水平,body,basic,headers
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                    //设置 请求的缓存的大小跟位置
                    val cacheFile = File(MyApplication.context.cacheDir, "cache")
                    val cache = Cache(cacheFile, 1024 * 1024 * 50) //50Mb 缓存的大小

                    client = OkHttpClient.Builder()
                            .addInterceptor(httpLoggingInterceptor)
                            .addInterceptor(CacheInterceptor(MyApplication.context))
                            .addInterceptor(RequestInterceptor())
                            .cache(cache)
                            .connectTimeout(60L, TimeUnit.SECONDS)
                            .readTimeout(60L, TimeUnit.SECONDS)
                            .writeTimeout(60L, TimeUnit.SECONDS)
                            .build()

                    retrofit = Retrofit.Builder()
                            .baseUrl(UriConstant.BASE_URL)
                            .client(client!!)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
            }
        }
        return retrofit
    }
}