package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.ext.requestWithSuccessCallback
import cn.xpcheng.playandroid.mvp.contract.HomeContract
import cn.xpcheng.playandroid.mvp.model.HomeModel
import cn.xpcheng.playandroid.mvp.model.bean.Article
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * @author ChengXinPing
 * @time   2020/1/16 11:14
 *
 */
class HomePresenter : BasePresenter<HomeModel, HomeContract.View>(), HomeContract.Presenter {

    override fun createModel(): HomeModel = HomeModel()

    override fun getBanner() {
        mModel!!.getBanner().requestWithSuccessCallback(mModel, mView, true) {
            mView!!.setBanner(it.data)
        }
    }

    override fun getHomeData() {

        getBanner()

        Observable.zip(mModel!!.getTopArticles(), mModel!!.getHomeArticles(0)
                , BiFunction<BaseResponse<MutableList<Article>>, BaseResponse<ArticleBody>,
                BaseResponse<ArticleBody>> { t1, t2 ->
            t1.data.forEach {
                it.isTop = true
            }
            t2.data.datas.addAll(0, t1.data)
            t2
        }).requestWithSuccessCallback(mModel, mView, true) {
            mView!!.setHomeData(it.data)
        }


    }

    override fun getHomeArticles(page: Int) {
        mModel!!.getHomeArticles(page).requestWithSuccessCallback(mModel, mView, false) {
            mView!!.setHomeData(it.data)
        }
    }
}