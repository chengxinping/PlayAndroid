package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author ChengXinPing
 * @time   2018/11/28 15:22
 *
 */
class KnowledgeListModel : BaseModel() {
    fun getKnowledgeList(page: Int, cid: Int): Observable<BaseResponse<ArticleBody>> {
        return RetrofitManager.service.getKnowledegList(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}