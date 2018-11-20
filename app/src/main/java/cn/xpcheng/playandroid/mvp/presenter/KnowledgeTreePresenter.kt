package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.mvp.contract.KnowledgeTreeContract
import cn.xpcheng.playandroid.mvp.model.bean.KnowledgeTreeModel

/**
 * @author ChengXinPing
 * @time   2018/11/7 15:58
 *
 */
class KnowledgeTreePresenter : BasePresenter<KnowledgeTreeContract.View>(), KnowledgeTreeContract.Presenter {

    private val knowledgeTreeModel: KnowledgeTreeModel by lazy {
        KnowledgeTreeModel()
    }

    override fun getKnowledgeTree() {
        mView?.showLoading()
        val disposable = knowledgeTreeModel.getKnowledgeTree()
                .subscribe({ results ->
                    mView?.apply {
                        if (results.errorCode != 0) {
                            showError(results.errorMsg)
                        } else {
                            onGetKnowledgeTreeSuccess(results.data)
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