package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.ext.requestWithSuccessCallback
import cn.xpcheng.playandroid.mvp.contract.KnowledgeListContract
import cn.xpcheng.playandroid.mvp.model.KnowledgeListModel

/**
 * @author ChengXinPing
 * @time   2018/11/28 15:24
 *
 */
class KnowledgeListPresenter : BasePresenter<KnowledgeListContract.Model, KnowledgeListContract.View>(), KnowledgeListContract.Presenter {

    override fun createModel(): KnowledgeListContract.Model? = KnowledgeListModel()

    override fun getKnowledgeList(page: Int, cid: Int) {
        mModel?.getKnowledgeList(page, cid)?.requestWithSuccessCallback(mModel, mView, true) {
            mView?.onGetKnowledgeListDone(it.data)
        }
    }
}


