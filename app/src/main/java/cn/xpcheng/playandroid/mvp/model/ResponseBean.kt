package cn.xpcheng.playandroid.mvp.model

import java.io.Serializable

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

//知识体系
data class KnowledgeTree(
        val children: MutableList<Knowledge>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val visible: Int
) : Serializable

data class Knowledge(
        val children: List<Any>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val visible: Int
) : Serializable