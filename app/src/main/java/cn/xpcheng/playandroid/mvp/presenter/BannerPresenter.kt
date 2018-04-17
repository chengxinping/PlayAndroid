package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.mvp.contract.BannerContract
import cn.xpcheng.playandroid.mvp.model.bean.BannerModel

/**
 * @author ChengXinPing
 * @time   2018/4/17 21:26
 *
 */
class BannerPresenter : BasePresenter<BannerContract.View>(), BannerContract.Presenter {

    private val mBannerModel: BannerModel by lazy { BannerModel() }

    override fun getBannerData() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = mBannerModel.getBannerData()
                .subscribe({ it ->
                    mRootView?.apply {
                        dismissLoading()
                        if (it.errorCode >= 0) {
                            showBanner(it.data!!)
                        } else {
                            showError(it.errorMsg, it.errorCode)
                        }
                    }
                })
        addSubscription(disposable)
    }
}