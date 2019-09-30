package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.ext.requestWithSuccessCallback
import cn.xpcheng.playandroid.mvp.contract.NavigationContract
import cn.xpcheng.playandroid.mvp.model.NavigationModel

/**
 * @author ChengXinPing
 * @time   2019/9/29 16:20
 *
 */
class NavigationPresenter : BasePresenter<NavigationContract.Model, NavigationContract.View>(), NavigationContract.Presenter {

    override fun createModel(): NavigationContract.Model? = NavigationModel()

    override fun getNavigationList() {
        mModel?.getNavigationList()?.requestWithSuccessCallback(mModel, mView, true) {
            mView?.onGetNavigationListSuccess(it.data)
        }
    }
}