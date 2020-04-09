package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.ext.requestWithSuccessCallback
import cn.xpcheng.playandroid.mvp.contract.SearchDetailContract
import cn.xpcheng.playandroid.mvp.model.SearchDetailModel

/**
 * @author ChengXinPing
 * @time   2020/4/9 10:08
 *
 */
class SearchDetailPresenter : BasePresenter<SearchDetailContract.Model, SearchDetailContract.View>(), SearchDetailContract.Presenter {

    override fun createModel(): SearchDetailContract.Model = SearchDetailModel()

    override fun search(key: String, page: Int) {
        mModel?.search(key, page)?.requestWithSuccessCallback(mModel, mView, true) {
            mView?.onSearchSuccess(it.data)
        }
    }
}