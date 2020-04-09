package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.contract.SearchDetailContract
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2020/4/9 10:04
 *
 */
class SearchDetailModel : BaseModel(), SearchDetailContract.Model {
    override fun search(key: String, page: Int): Observable<BaseResponse<ArticleBody>> = RetrofitManager.service.search(page, key)
}