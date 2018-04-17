package cn.xpcheng.playandroid.base

/**
 * @author ChengXinPing
 * @time   2018/4/17 21:12
 *
 */

interface IPresenter<in V : IBaseView> {

    fun attachView(mRootView: V)

    fun detachView()

}