package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.contract.ProjectListContract
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2019/10/17 14:55
 *
 */
class ProjectListModel : BaseModel(), ProjectListContract.Model {
    override fun getRecentlyProjects(page: Int): Observable<BaseResponse<ArticleBody>> = RetrofitManager.service.getRecentlyProjects(page)

    override fun getProjectList(page: Int, cid: Int): Observable<BaseResponse<ArticleBody>> = RetrofitManager.service.getProjectList(page, cid);
}