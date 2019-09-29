package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.ext.requestWithSuccessCallback
import cn.xpcheng.playandroid.mvp.contract.KnowledgeTreeContract
import cn.xpcheng.playandroid.mvp.model.KnowledgeTreeModel

/**
 * @author ChengXinPing
 * @time   2018/11/7 15:58
 *
 */
class KnowledgeTreePresenter : BasePresenter<KnowledgeTreeContract.Model, KnowledgeTreeContract.View>(), KnowledgeTreeContract.Presenter {

    override fun createModel(): KnowledgeTreeContract.Model? = KnowledgeTreeModel()

    override fun getKnowledgeTree() {
        mModel?.getKnowledgeTree()?.requestWithSuccessCallback(mModel, mView, true) {
            mView?.onGetKnowledgeTreeSuccess(it.data)
        }
    }
}