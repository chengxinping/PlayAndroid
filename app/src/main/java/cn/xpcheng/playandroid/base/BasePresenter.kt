package cn.xpcheng.playandroid.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author ChengXinPing
 * @time   2018/10/31 11:36
 *
 */
abstract class BasePresenter<V : IView> : IPresenter<V>, LifecycleObserver {

    var mView: V? = null
        private set

    private val isViewAttached: Boolean
        get() = mView != null


    private var mCompositeDisposable: CompositeDisposable? = null

    override fun attachView(mView: V) {
        mCompositeDisposable = CompositeDisposable()
        this.mView = mView
        if (mView is LifecycleOwner) {
            mView.lifecycle.addObserver(this)
        }

    }

    override fun detachView() {
        //解绑
        unDispose()
        mView = null
    }

    open fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    open fun addSubscription(disposable: Disposable) {
        mCompositeDisposable?.add(disposable)
    }

    private fun unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
        }
        mCompositeDisposable = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy(owner: LifecycleOwner) {
        detachView()
        owner.lifecycle.removeObserver(this)
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")
}