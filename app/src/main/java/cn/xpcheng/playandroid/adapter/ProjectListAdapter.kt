package cn.xpcheng.playandroid.adapter

import android.text.Html
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.mvp.model.bean.Article
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author ChengXinPing
 * @time   2019/10/31 10:58
 *
 */
class ProjectListAdapter(list: MutableList<Article>) : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_project, list) {

    override fun convert(helper: BaseViewHolder, item: Article?) {
        helper.setText(R.id.tv_item_project_title, Html.fromHtml(item?.title))
                .setText(R.id.tv_item_project_desc, Html.fromHtml(item?.desc))
                .setText(R.id.tv_item_project_author, item?.author)
                .setText(R.id.tv_item_project_date, item?.niceDate)
                .setImageResource(R.id.iv_item_project, if (item!!.collect) R.drawable.ic_red_love else R.drawable.ic_grey_love)

        val options = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        Glide.with(mContext)
                .load(item.envelopePic)
                .apply(options)
                .into(helper.getView(R.id.iv_item_project))
    }
}