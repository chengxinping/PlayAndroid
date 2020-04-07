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

    /**分类下面的文章
     * http://www.wanandroid.com/article/list/0/json?cid=60
     *cid 分类的id，上述二级目录的id  页码：拼接在链接上，从0开始。
     */
    @GET("article/list/{page}/json")
    fun getKnowledgeList(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ArticleBody>>


    /**
     *置顶文章
     */
    @GET("article/top/json")
    fun getTopArticles(): Observable<BaseResponse<MutableList<Article>>>

    /**
     * 首页文章列表
     * 页码，拼接在连接中，从0开始。
     *
     */
    @GET("article/list/{page}/json")
    fun getArticlesList(@Path("page") page: Int): Observable<BaseResponse<ArticleBody>>

    /**
     * 获取导航数据
     */
    @GET("navi/json")
    fun getNavigation(): Observable<BaseResponse<List<NavigationBean>>>

    /**
     * 获取项目分类
     */
    @GET("project/tree/json")
    fun getProjectTree(): Observable<BaseResponse<List<ProjectTreeBean>>>

    /**
     * 获取某个项目分类下全部数据
     * @param page 分页数据
     * @param cid 项目分类id
     */
    @GET("project/list/{page}/json")
    fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ArticleBody>>

    /**
     * 最新项目
     * @param page 分页数据
     */
    @GET("article/listproject/{page}/json")
    fun getRecentlyProjects(@Path("page") page: Int): Observable<BaseResponse<ArticleBody>>

    /**
     * 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    fun getWechatTree(): Observable<BaseResponse<List<ProjectTreeBean>>>

    /**
     * 查看某个公众号历史数据
     */
    @GET("wxarticle/list/{cid}/{page}/json")
    fun getWechatList(@Path("cid") cid: Int, @Path("page") page: Int): Observable<BaseResponse<ArticleBody>>
}