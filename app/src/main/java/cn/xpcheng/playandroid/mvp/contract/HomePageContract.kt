package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.Banner

/**
 * @author ChengXinPing
 * @time   2018/11/1 9:21
 *
 */
interface HomePageContract {
    interface View : IView {
        fun onGetBannerSuccess(banners: List<Banner>)
    }

    interface Presenter : IPresenter<View> {
        fun getBanner()
    }
}