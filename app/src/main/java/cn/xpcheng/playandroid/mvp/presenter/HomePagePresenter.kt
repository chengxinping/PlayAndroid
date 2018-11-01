package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.mvp.contract.HomePageContract
import cn.xpcheng.playandroid.mvp.model.HomePageModel

/**
 * @author ChengXinPing
 * @time   2018/11/1 9:30
 *
 */
class HomePagePresenter : BasePresenter<HomePageContract.View>(), HomePageContract.Presenter {

    private val homePageModel: HomePageModel by lazy {
        HomePageModel()
    }

    override fun getBanner() {
        mView?.showLoading()
        val disposable = homePageModel.getBanners()
                .subscribe({ results ->
                    mView?.apply {
                        if (results.errorCode != 0) {
                            showError(results.errorMsg)
                        } else {
                            onGetBannerSuccess(results.data)
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