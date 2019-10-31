package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.contract.ProjectContract
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import cn.xpcheng.playandroid.mvp.model.bean.ProjectTreeBean
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2019/10/17 14:36
 *
 */
class ProjectModel : BaseModel(), ProjectContract.Model {

    override fun getProgectTree(): Observable<BaseResponse<List<ProjectTreeBean>>> {
        return RetrofitManager.service.getProjectTree()
    }
}