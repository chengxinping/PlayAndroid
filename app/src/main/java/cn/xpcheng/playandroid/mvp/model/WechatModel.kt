package cn.xpcheng.playandroid.mvp.model

import cn.xpcheng.playandroid.base.BaseModel
import cn.xpcheng.playandroid.http.RetrofitManager
import cn.xpcheng.playandroid.mvp.contract.ListContract
import cn.xpcheng.playandroid.mvp.model.bean.BaseResponse
import cn.xpcheng.playandroid.mvp.model.bean.ProjectTreeBean
import io.reactivex.Observable


/**
 * @author ChengXinPing
 * @time   2019/12/27 14:15
 *
 */
class WechatModel : BaseModel(), ListContract.Model {
    override fun getTree(): Observable<BaseResponse<List<ProjectTreeBean>>> {
        return RetrofitManager.service.getWechatTree()
    }

}