package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IBaseView
import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.mvp.model.bean.BannerBean

/**
 * @author ChengXinPing
 * @time   2018/4/17 21:23
 *
 */
interface BannerContract {

    interface View : IBaseView {
        /**
         *显示分类信息
         */
        fun showBanner(bannerBeans: List<BannerBean>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String, errorCode: Int)

    }

    interface Presenter : IPresenter<View> {
        /**
         * 获取分类的信息
         */
        fun getBannerData()
    }
}