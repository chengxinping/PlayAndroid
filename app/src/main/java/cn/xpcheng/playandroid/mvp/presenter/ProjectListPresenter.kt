package cn.xpcheng.playandroid.mvp.presenter

import cn.xpcheng.playandroid.base.BasePresenter
import cn.xpcheng.playandroid.ext.requestWithSuccessCallback
import cn.xpcheng.playandroid.mvp.contract.ProjectListContract
import cn.xpcheng.playandroid.mvp.model.ProjectListModel

/**
 * @author ChengXinPing
 * @time   2019/10/17 14:56
 *
 */
class ProjectListPresenter : BasePresenter<ProjectListContract.Model, ProjectListContract.View>(), ProjectListContract.Presenter {
    override fun getRecentlyProjects(page: Int) {
        mModel?.getRecentlyProjects(page)?.requestWithSuccessCallback(mModel, mView, true) {
            mView?.onGetProjectListSuccess(it.data)
        }
    }

    override fun createModel(): ProjectListContract.Model? = ProjectListModel()

    override fun getProjectList(page: Int, cid: Int) {
        mModel?.getProjectList(page, cid)?.requestWithSuccessCallback(mModel, mView, true) {
            mView?.onGetProjectListSuccess(it.data)
        }
    }
}