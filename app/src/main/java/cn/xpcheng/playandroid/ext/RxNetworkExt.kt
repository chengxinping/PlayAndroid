package cn.xpcheng.playandroid.ext

import cn.xpcheng.playandroid.App
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.base.IModel
import cn.xpcheng.playandroid.base.IView
import cn.xpcheng.playandroid.mvp.model.bean.BaseBean
import cn.xpcheng.playandroid.rx.SchedulerUtils
import cn.xpcheng.playandroid.utils.NetworkUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author ChengXinPing
 * @time   2019/9/29 11:47
 * rxjava+retrofit网络请求的扩展函数  减少重复代码
 *
 */

fun <T : BaseBean> Observable<T>.requestWithSuccessCallback(
        model: IModel?,
        view: IView?,
        isShowLoading: Boolean = true,
        onSuccess: (T) -> Unit
) {
    compose(SchedulerUtils.ioToAndroidMain())
            .subscribe(object : Observer<T> {
                override fun onSubscribe(d: Disposable) {
                    if (isShowLoading)
                        view?.showLoading()
                    model?.addDisposable(d)
                    if (!NetworkUtils.isNetworkConnected(App.instance)) {
                        view?.showError(App.instance.resources.getString(R.string.network_unavailable_tip))
                        onComplete()
                    }
                }

                override fun onNext(t: T) {

                    when (t.errorCode) {
                        0 -> onSuccess.invoke(t)
                        else -> view?.showError(t.errorMsg)
                    }

                }

                override fun onComplete() {
                    view?.hideLoading()
                }

                override fun onError(e: Throwable) {
                    view?.hideLoading()
                    view?.showError(e.message.toString())
                }

            })
}
