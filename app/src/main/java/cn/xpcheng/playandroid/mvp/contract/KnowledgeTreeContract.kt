package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.KnowledgeTree

/**
 * @author ChengXinPing
 * @time   2018/11/7 15:23
 *
 */
interface KnowledgeTreeContract {
    interface View : IView {
        fun onGetKnowledgeTreeSuccess(knowledgeTree:List<KnowledgeTree>)
    }

    interface Presenter : IPresenter<View> {
        fun getKnowledgeTree()
    }
}