package cn.xpcheng.playandroid.base

import android.os.Bundle
import android.view.View

/**
 * @author ChengXinPing
 * @time   2018/10/31 17:10
 *
 */
abstract class BaseMVPFragment<in V : IView, P : IPresenter<V>> : BaseFragment(), IView {

    protected var mPresenter: P? = null

    /**
     * 初始化presenter 子类实现
     */
    protected abstract fun createPresenter(): P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = createPresenter()
        if (mPresenter != null) {
            mPresenter!!.attachView(this as V)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
        mPresenter = null
    }
}