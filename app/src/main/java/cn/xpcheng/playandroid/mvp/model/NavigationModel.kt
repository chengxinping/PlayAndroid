package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.contract.NavigationContract
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import cn.xpcheng.playandroid.mvp.model.bean.NavigationBean
import io.reactivex.Observable

/**
 * @author ChengXinPing
 * @time   2019/9/29 16:18
 *
 */
class NavigationModel : BaseModel(), NavigationContract.Model {
    override fun getNavigationList(): Observable<BaseResponse<List<NavigationBean>>> {
        return RetrofitManager.service.getNavigation()
    }
}