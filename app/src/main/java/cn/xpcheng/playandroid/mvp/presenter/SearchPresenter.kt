package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.ext.requestWithSuccessCallback
import cn.xpcheng.playandroid.mvp.contract.SearchContract
import cn.xpcheng.playandroid.mvp.model.SearchModel
import cn.xpcheng.playandroid.mvp.model.bean.SearchHistory
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.litepal.LitePal

/**
 * @author ChengXinPing
 * @time   2020/4/7 16:08
 *
 */
class SearchPresenter : BasePresenter<SearchContract.Model, SearchContract.View>(), SearchContract.Presenter {

    override fun createModel(): SearchContract.Model = SearchModel()

    override fun getHotKeys() {
        mModel!!.getHotKeys().requestWithSuccessCallback(mModel, mView, true) {
            mView?.onGetHotKeysSuccess(it.data)
        }
    }

    override fun saveSearchKey(key: String) {

        val histories = LitePal.where("key='${key.trim()}'").find(SearchHistory::class.java)
        if (histories.isEmpty()) {
            SearchHistory(key).save()
        }
    }

    override fun getHistoryKeys() {
        Observable.create(ObservableOnSubscribe<MutableList<SearchHistory>> {
            val historyKeys = LitePal.findAll(SearchHistory::class.java)
            it.onNext(historyKeys)
            it.onComplete()
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<MutableList<SearchHistory>>() {

                    override fun onComplete() {
                    }

                    override fun onNext(t: MutableList<SearchHistory>) {
                        mView?.onGetHistoryKeysSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        mView?.showError(e.message.toString())
                    }
                })
    }

    override fun deleteHistory() {
        Observable.create(ObservableOnSubscribe<Any> {
            LitePal.deleteAll(SearchHistory::class.java)
            it.onComplete()
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<Any>() {

                    override fun onComplete() {
                        mView?.onDeleteHistorySuccess()
                    }

                    override fun onNext(t: Any) {

                    }

                    override fun onError(e: Throwable) {
                        mView?.showError(e.message.toString())
                    }
                })
    }
}