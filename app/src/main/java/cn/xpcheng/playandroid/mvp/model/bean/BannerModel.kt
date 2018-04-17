package cn.xpcheng.playandroid.mvp.model.bean

import cn.xpcheng.playandroid.network.RetrofitManager
import cn.xpcheng.playandroid.network.base.BaseResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author ChengXinPing
 * @time   2018/4/17 21:31
 *
 */
class BannerModel {

    /**
     *获取首页banner信息
     */
    fun getBannerData(): Observable<BaseResponse<List<BannerBean>>> {
        return RetrofitManager.service.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}