package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.mvp.contract.KnowledgeListContract
import cn.xpcheng.playandroid.mvp.model.KnowledgeListModel

/**
 * @author ChengXinPing
 * @time   2018/11/28 15:24
 *
 */
class KnowledgeListPresenter : BasePresenter<KnowledgeListContract.View>(), KnowledgeListContract.Presenter {

    private val knowledgeListModel: KnowledgeListModel by lazy {
        KnowledgeListModel()
    }

    override fun getKnowledgeList(page: Int, cid: Int) {
        mView?.showLoading()
        val disposable = knowledgeListModel.getKnowledgeList(page, cid)
                .subscribe({ results ->
                    mView?.apply {
                        if (results.errorCode != 0) {
                            showError(results.errorMsg)
                        } else {
                            onGetKonwledgeListDone(results.data)
                        }
                        hideLoading()
                    }
                }, { t ->
                    mView?.apply {
                        hideLoading()
                        showError(t.message!!)
                    }
                })
        addSubscription(disposable)
    }
}