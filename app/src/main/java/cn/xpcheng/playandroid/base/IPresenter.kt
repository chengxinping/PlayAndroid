package cn.xpcheng.playandroid.base

/**
 * @author ChengXinPing
 * @time   2018/10/31 11:29
 *
 */
interface IPresenter<in V : IView> {
    fun attachView(mView: V)

    fun detachView()

}