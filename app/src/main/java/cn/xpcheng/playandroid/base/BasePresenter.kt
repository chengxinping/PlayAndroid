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
abstract class BasePresenter<M : IModel, V : IView> : IPresenter<V>, LifecycleObserver {

    protected var mModel: M? = null
    protected var mView: V? = null


    private val isViewAttached: Boolean
        get() = mView != null


    private var mCompositeDisposable: CompositeDisposable? = null

    /**
     * 创建model
     */
    open fun createModel(): M? = null

    override fun attachView(mView: V) {
        this.mView = mView
        mModel = createModel()
        if (mView is LifecycleOwner) {
            mView.lifecycle.addObserver(this)
            if (mModel != null && mModel is LifecycleObserver) {
                mView.lifecycle.addObserver(mModel as LifecycleObserver)
            }
        }

    }

    override fun detachView() {
        // 保证activity结束时取消所有正在执行的订阅
        unDispose()
        mModel?.onDetach()
        mView = null
        mModel = null
        mCompositeDisposable = null
    }

    open fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    open fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let {
            mCompositeDisposable?.add(it)
        }
    }

    private fun unDispose() {
        mCompositeDisposable?.clear()  // 保证Activity结束时取消
        mCompositeDisposable = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")
}