package cn.xpcheng.playandroid.adapter

import android.text.Html
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.mvp.model.bean.Article
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author ChengXinPing
 * @time   2020/1/16 16:50
 *
 */
class HomeAdapter(list: MutableList<Article>) : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_home_article, list) {
    override fun convert(helper: BaseViewHolder, item: Article?) {
        item ?: return
        val authorStr = if (item.author.isNotEmpty()) item.author else item.shareUser
        val chapterName: String = when {
            item.superChapterName.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }

        helper.setText(R.id.tv_item_author, authorStr)
                .setText(R.id.tv_item_title, Html.fromHtml(item.title))
                .setText(R.id.tv_item_time, item.niceDate)
                .setImageResource(R.id.iv_item_love,
                        if (item.collect) R.drawable.ic_red_love else R.drawable.ic_grey_love
                )
                .setText(R.id.tv_item_type, chapterName)
                .setGone(R.id.tv_item_top, item.isTop)
                .setGone(R.id.tv_item_new, item.fresh)

        if (item.tags.isNotEmpty()) {
            helper.setGone(R.id.tv_item_tag, true)
                    .setText(R.id.tv_item_tag, item.tags[0].name)
        } else {
            helper.setGone(R.id.tv_item_tag, false)
        }

    }
}