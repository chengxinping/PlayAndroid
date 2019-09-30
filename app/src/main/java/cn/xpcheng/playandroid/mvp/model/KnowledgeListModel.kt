package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.contract.KnowledgeListContract
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2018/11/28 15:22
 *
 */
class KnowledgeListModel : BaseModel(), KnowledgeListContract.Model {
    override fun getKnowledgeList(page: Int, cid: Int): Observable<BaseResponse<ArticleBody>> {
        return RetrofitManager.service.getKnowledgeList(page, cid)
    }
}