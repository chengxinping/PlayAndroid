package cn.xpcheng.playandroid.api

import cn.xpcheng.playandroid.mvp.model.Banner
import cn.xpcheng.playandroid.mvp.model.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author ChengXinPing
 * @time   2018/4/17 20:52
 *
 */
interface ApiService {
    @GET("banner/json")
    fun getBanners(): Observable<BaseResponse<List<Banner>>>
}