package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IModel
import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2020/4/9 9:59
 *
 */
interface SearchDetailContract {
    interface View : IView {
        fun onSearchSuccess(articleBody: ArticleBody)

        fun scrollToTop()
    }

    interface Presenter : IPresenter<View> {
        fun search(key: String, page: Int)
    }

    interface Model : IModel {
        fun search(key: String, page: Int): Observable<BaseResponse<ArticleBody>>
    }
}