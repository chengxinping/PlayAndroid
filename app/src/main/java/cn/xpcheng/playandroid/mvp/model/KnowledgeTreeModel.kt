package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import cn.xpcheng.playandroid.mvp.model.bean.KnowledgeTree
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author ChengXinPing
 * @time   2018/11/7 15:46
 *
 */
class KnowledgeTreeModel : BaseModel() {
    fun getKnowledgeTree(): Observable<BaseResponse<List<KnowledgeTree>>> {
        return RetrofitManager.service.getKnowledgeTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}