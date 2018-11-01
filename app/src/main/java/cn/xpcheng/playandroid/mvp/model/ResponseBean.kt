package cn.xpcheng.playandroid.mvp.model

/**
 * @author ChengXinPing
 * @time   2018/11/1 9:25
 *
 */
data class BaseResponse<T>(val data: T,
                           val errorCode: Int,
                           val errorMsg: String)


/**
 *轮播图
 */
data class Banner(val desc: String,
                  val id: Int,
                  val imagePath: String,
                  val isVisible: Int,
                  val order: Int,
                  val title: String,
                  val type: String,
                  val url: String)