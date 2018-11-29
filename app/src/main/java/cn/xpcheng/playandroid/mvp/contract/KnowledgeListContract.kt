package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody

/**
 * @author ChengXinPing
 * @time   2018/11/28 14:46
 *
 */
interface KnowledgeListContract {
    interface View : IView {
        fun onGetKonwledgeListDone(articles: ArticleBody)
    }

    interface Presenter : IPresenter<View> {
        fun getKnowledgeList(page: Int, cid: Int)
    }
}