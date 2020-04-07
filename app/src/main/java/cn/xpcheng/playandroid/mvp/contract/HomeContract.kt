package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IModel
import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.bean.Article
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.Banner
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2020/1/16 10:13
 *
 */
interface HomeContract {
    interface View : IView {
        fun setBanner(banner: List<Banner>)

        fun setHomeData(articleBody: ArticleBody)

        fun scrollToTop()
    }

    interface Presenter : IPresenter<View> {
        fun getBanner()

        fun getHomeData()

        fun getHomeArticles(page: Int)
    }

    interface Model : IModel {
        fun getBanner(): Observable<BaseResponse<List<Banner>>>

        fun getTopArticles(): Observable<BaseResponse<MutableList<Article>>>

        fun getHomeArticles(page: Int): Observable<BaseResponse<ArticleBody>>
    }
}