package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IModel
import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import cn.xpcheng.playandroid.mvp.model.bean.KnowledgeTree
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2018/11/7 15:23
 *
 */
interface KnowledgeTreeContract {
    interface View : IView {
        fun onGetKnowledgeTreeSuccess(knowledgeTree: List<KnowledgeTree>)

        fun scrollToTop()
    }

    interface Presenter : IPresenter<View> {
        fun getKnowledgeTree()
    }

    interface Model : IModel {
        fun getKnowledgeTree(): Observable<BaseResponse<List<KnowledgeTree>>>
    }
}