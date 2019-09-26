package cn.xpcheng.playandroid.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.mvp.model.bean.Article
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author ChengXinPing
 * @time   2018/11/29 9:43
 *
 */
class ArticleListAdapter(private val context: Context?, list: MutableList<Article>) : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article, list) {
    override fun convert(helper: BaseViewHolder, item: Article?) {
        item ?: return
        helper.setText(R.id.item_tv_article_title, item.title)
                .setText(R.id.item_tv_article_author, item.author)
                .setText(R.id.item_tv_article_date, item.niceDate)
                .setImageResource(R.id.item_iv_article_love,
                        if (item.collect) R.drawable.ic_red_love else R.drawable.ic_grey_love
                )
                .setText(R.id.item_tv_article_sort,
                        when {
                            item.superChapterName.isNotEmpty() && item.chapterName.isNotEmpty() -> "${item.superChapterName} / ${item.chapterName}"
                            item.superChapterName.isNotEmpty() -> item.superChapterName
                            item.chapterName.isNotEmpty() -> item.chapterName
                            else -> ""
                        })
                .addOnClickListener(R.id.item_iv_article_love)

        if (!TextUtils.isEmpty(item.envelopePic)) {
            helper.getView<ImageView>(R.id.item_iv_article_img).visibility = View.VISIBLE
            val options = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
            Glide.with(context!!)
                    .load(item.envelopePic)
                    .apply(options)
                    .into(helper.getView<ImageView>(R.id.item_iv_article_img))

        } else {
            helper.getView<ImageView>(R.id.item_iv_article_img).visibility = View.GONE
        }
    }
}