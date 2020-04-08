package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.contract.SearchContract
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import cn.xpcheng.playandroid.mvp.model.bean.HotKey
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2020/4/7 16:06
 *
 */
class SearchModel : BaseModel(), SearchContract.Model {
    override fun getHotKeys(): Observable<BaseResponse<MutableList<HotKey>>> = RetrofitManager.service.getHotKeys()
}