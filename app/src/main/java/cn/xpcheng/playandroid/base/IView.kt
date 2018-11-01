package cn.xpcheng.playandroid.base

/**
 * @author ChengXinPing
 * @time   2018/10/31 11:28
 *
 */
interface IView {
    fun showLoading()

    fun hideLoading()

    fun showError(errorMsg: String)
}