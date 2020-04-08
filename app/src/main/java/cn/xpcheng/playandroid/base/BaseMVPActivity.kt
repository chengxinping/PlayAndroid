package cn.xpcheng.playandroid.base

import cn.xpcheng.playandroid.ext.toast
import com.fengchen.uistatus.annotation.UiStatus

/**
 * @author ChengXinPing
 * @time   2018/10/31 16:18
 *
 */
abstract class BaseMVPActivity<in V : IView, P : IPresenter<V>> : BaseActivity(), IView {

    protected var mPresenter: P? = null

    /**
     * 初始化presenter 子类实现
     */
    protected abstract fun createPresenter(): P


    override fun initData() {
        mPresenter = createPresenter()
        if (mPresenter != null) {
            mPresenter!!.attachView(this as V)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
        mPresenter = null
    }

    override fun showError(errorMsg: String) {
        errorMsg.toast(this)
    }

    override fun showLoading() {
        mUiStatusController.changeUiStatusIgnore(UiStatus.LOADING)
    }

    override fun hideLoading() {
        mUiStatusController.changeUiStatusIgnore(UiStatus.CONTENT)
    }
}