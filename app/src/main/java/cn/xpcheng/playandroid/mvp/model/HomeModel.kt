package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.contract.HomeContract
import cn.xpcheng.playandroid.mvp.model.bean.Article
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.Banner
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2020/1/16 10:40
 *
 */
class HomeModel : BaseModel(), HomeContract.Model {
    override fun getBanner(): Observable<BaseResponse<List<Banner>>> = RetrofitManager.service.getBanners()

    override fun getTopArticles(): Observable<BaseResponse<MutableList<Article>>> = RetrofitManager.service.getTopArticles()

    override fun getHomeArticles(page: Int): Observable<BaseResponse<ArticleBody>> = RetrofitManager.service.getArticlesList(page)
}