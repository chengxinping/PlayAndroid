package cn.xpcheng.playandroid.mvp.model.bean

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

//知识体系大列表
data class KnowledgeTree(
        val children: MutableList<Knowledge>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val visible: Int
) : Serializable

//知识体系小列表
data class Knowledge(
        val children: List<Any>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val visible: Int
) : Serializable


//文章列表
data class ArticleBody(
        val curPage: Int,
        val datas: MutableList<Article>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)

//文章详情
data class Article(
        val apkLink: String,
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val envelopePic: String,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        val niceDate: String,
        val origin: String,
        val projectLink: String,
        val publishTime: Long,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: MutableList<Tag>,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
)

data class Tag(
        val name: String,
        val url: String
)