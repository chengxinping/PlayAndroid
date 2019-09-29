package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView

/**
 * @author ChengXinPing
 * @time   2019/9/29 10:54
 *
 */
interface NavigationContract {
    interface View : IView {

    }

    interface Presenter : IPresenter<View> {

    }
}