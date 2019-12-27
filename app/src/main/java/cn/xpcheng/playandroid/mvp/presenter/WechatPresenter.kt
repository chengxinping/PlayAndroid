package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.ext.requestWithSuccessCallback
import cn.xpcheng.playandroid.mvp.contract.ListContract
import cn.xpcheng.playandroid.mvp.model.WechatModel

/**
 * @author ChengXinPing
 * @time   2019/10/17 14:38
 *
 */
class WechatPresenter : BasePresenter<ListContract.Model, ListContract.View>(), ListContract.Presenter {

    override fun createModel(): ListContract.Model? = WechatModel()

    override fun getTree() {
        mModel?.getTree()?.requestWithSuccessCallback(mModel, mView, true) {
            mView?.onGetTreeSuccess(it.data)
        }
    }
}