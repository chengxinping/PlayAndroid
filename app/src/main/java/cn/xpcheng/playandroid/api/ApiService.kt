package cn.xpcheng.playandroid.api

import cn.xpcheng.playandroid.mvp.model.bean.BannerBean
import cn.xpcheng.playandroid.network.base.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author ChengXinPing
 * @time   2018/4/17 20:52
 *
 */
interface ApiService {

    // 首页banner
    @GET("banner/json")
    fun getBanner(): Observable<BaseResponse<List<BannerBean>>>
}