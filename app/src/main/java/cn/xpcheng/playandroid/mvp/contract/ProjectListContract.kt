package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IModel
import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2019/10/17 14:29
 *
 */
interface ProjectListContract {
    interface View : IView {
        fun onGetProjectListSuccess(articleBody: ArticleBody)

        fun scrollToTop()
    }

    interface Presenter : IPresenter<View> {
        fun getProjectList(page: Int, cid: Int)

        fun getRecentlyProjects(page: Int)
    }

    interface Model : IModel {
        fun getProjectList(page: Int, cid: Int): Observable<BaseResponse<ArticleBody>>

        fun getRecentlyProjects(page: Int): Observable<BaseResponse<ArticleBody>>
    }
}