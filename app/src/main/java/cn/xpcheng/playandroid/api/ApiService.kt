package cn.xpcheng.playandroid.api

import cn.xpcheng.playandroid.mvp.model.bean.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author ChengXinPing
 * @time   2018/4/17 20:52
 *
 */
interface ApiService {
    /**
     * 首页轮播图
     */
    @GET("banner/json")
    fun getBanners(): Observable<BaseResponse<List<Banner>>>

    /**知识体系
     * http://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    fun getKnowledgeTree(): Observable<BaseResponse<List<KnowledgeTree>>>

    /**知识体系下面的文章
     * http://www.wanandroid.com/article/list/0/json?cid=60
     *cid 分类的id，上述二级目录的id  页码：拼接在链接上，从0开始。
     */
    @GET("article/list/{page}/json")
    fun getKnowledgeList(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ArticleBody>>

    /**
     * 获取导航数据
     */
    @GET("navi/json")
    fun getNavigation(): Observable<BaseResponse<List<NavigationBean>>>
}