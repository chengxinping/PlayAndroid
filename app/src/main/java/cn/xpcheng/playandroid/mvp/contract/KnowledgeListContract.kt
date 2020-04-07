package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IModel
import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2018/11/28 14:46
 *
 */
interface KnowledgeListContract {
    interface View : IView {
        fun onGetKnowledgeListDone(articles: ArticleBody)

        fun scrollToTop()
    }

    interface Presenter : IPresenter<View> {
        fun getKnowledgeList(page: Int, cid: Int)
    }

    interface Model : IModel {
        fun getKnowledgeList(page: Int, cid: Int): Observable<BaseResponse<ArticleBody>>
    }
}