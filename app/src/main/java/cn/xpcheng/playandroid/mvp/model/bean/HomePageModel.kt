package cn.xpcheng.playandroid.mvp.model.bean

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.model.Banner
import cn.xpcheng.playandroid.mvp.model.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author ChengXinPing
 * @time   2018/11/1 9:39
 *
 */
class HomePageModel : BaseModel() {
    fun getBanners(): Observable<BaseResponse<List<Banner>>> {
        return RetrofitManager.service.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}