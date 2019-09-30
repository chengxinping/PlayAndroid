package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.contract.KnowledgeTreeContract
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import cn.xpcheng.playandroid.mvp.model.bean.KnowledgeTree
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2018/11/7 15:46
 *
 */
class KnowledgeTreeModel : BaseModel(), KnowledgeTreeContract.Model {

    override fun getKnowledgeTree(): Observable<BaseResponse<List<KnowledgeTree>>> {
        return RetrofitManager.service.getKnowledgeTree()
    }
}