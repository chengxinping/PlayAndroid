package cn.xpcheng.playandroid.api

import cn.xpcheng.playandroid.mvp.model.Banner
import cn.xpcheng.playandroid.mvp.model.BaseResponse
import cn.xpcheng.playandroid.mvp.model.KnowledgeTree
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author ChengXinPing
 * @time   2018/4/17 20:52
 *
 */
interface ApiService {
    //首页轮播图
    @GET("banner/json")
    fun getBanners(): Observable<BaseResponse<List<Banner>>>

    //知识体系
    @GET("tree/json")
    fun getKnowledgeTree(): Observable<BaseResponse<List<KnowledgeTree>>>
}