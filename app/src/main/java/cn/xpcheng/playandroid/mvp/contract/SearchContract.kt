package cn.xpcheng.playandroid.mvp.contract

import cn.xpcheng.playandroid.base.IModel
import cn.xpcheng.playandroid.base.IPresenter
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import cn.xpcheng.playandroid.mvp.model.bean.HotKey
import cn.xpcheng.playandroid.mvp.model.bean.SearchHistory
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2020/4/7 15:47
 *
 */
interface SearchContract {
    interface View : IView {
        fun onGetHotKeysSuccess(hotKeys: MutableList<HotKey>)

        fun onGetHistoryKeysSuccess(keys: MutableList<SearchHistory>)

        fun onDeleteHistorySuccess()
    }

    interface Presenter : IPresenter<View> {
        fun getHotKeys()

        fun saveSearchKey(key: String)

        fun getHistoryKeys()

        fun deleteHistory()

    }

    interface Model : IModel {
        fun getHotKeys(): Observable<BaseResponse<MutableList<HotKey>>>
    }
}