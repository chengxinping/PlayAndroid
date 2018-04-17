package cn.xpcheng.playandroid.network.base

/**
 * @author ChengXinPing
 * @time   2018/4/17 10:57
 *
 */
class BaseResponse<T> {
    var data: T? = null

    // errorCode如果为负数则认为错误
    var errorCode: Int = 0

    // errorCode如果为负数才有  errorMsg会包含错误信息
    var errorMsg: String = ""
}