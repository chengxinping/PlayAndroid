package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.ext.requestWithSuccessCallback
import cn.xpcheng.playandroid.mvp.contract.ProjectContract
import cn.xpcheng.playandroid.mvp.model.ProjectModel

/**
 * @author ChengXinPing
 * @time   2019/10/17 14:38
 *
 */
class ProjectPresenter : BasePresenter<ProjectContract.Model, ProjectContract.View>(), ProjectContract.Presenter {

    override fun createModel(): ProjectContract.Model? = ProjectModel()

    override fun getProgectTree() {
        mModel?.getProgectTree()?.requestWithSuccessCallback(mModel, mView, true) {
            mView?.onGetProjectTreeSuccess(it.data)
        }
    }
}