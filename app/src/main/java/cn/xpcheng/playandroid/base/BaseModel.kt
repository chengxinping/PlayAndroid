package cn.xpcheng.playandroid.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author ChengXinPing
 * @time   2018/11/1 9:40
 *
 */
open class BaseModel : IModel, LifecycleObserver {
    override fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let { mCompositeDisposable?.add(it) }
    }

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onDetach() {
        unDispose()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }

    private fun unDispose() {
        mCompositeDisposable?.clear()  // 保证Activity结束时取消
        mCompositeDisposable = null
    }
}