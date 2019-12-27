package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IModel
import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import cn.xpcheng.playandroid.mvp.model.bean.ProjectTreeBean
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2019/10/17 14:29
 *
 */
interface ListContract {
    interface View : IView {
        fun onGetTreeSuccess(list: List<ProjectTreeBean>)
    }

    interface Presenter : IPresenter<View> {
        fun getTree()
    }

    interface Model : IModel {
        fun getTree(): Observable<BaseResponse<List<ProjectTreeBean>>>
    }
}