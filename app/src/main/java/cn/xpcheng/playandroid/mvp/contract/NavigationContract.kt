package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IModel
import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import cn.xpcheng.playandroid.mvp.model.bean.NavigationBean
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2019/9/29 10:54
 *
 */
interface NavigationContract {
    interface View : IView {
        fun onGetNavigationListSuccess(list: List<NavigationBean>)
    }

    interface Presenter : IPresenter<View> {
        fun getNavigationList()
    }

    interface Model : IModel {
        fun getNavigationList(): Observable<BaseResponse<List<NavigationBean>>>
    }
}