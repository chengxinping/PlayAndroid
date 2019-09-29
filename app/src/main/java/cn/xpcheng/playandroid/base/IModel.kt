package cn.xpcheng.playandroid.base

import io.reactivex.disposables.Disposable

/**
 * @author ChengXinPing
 * @time   2018/11/1 9:40
 *
 */
interface IModel {
    fun addDisposable(disposable: Disposable?)

    fun onDetach()
}