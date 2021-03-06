package cn.xpcheng.playandroid.adapter

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.constant.Constant
import cn.xpcheng.playandroid.mvp.model.bean.Article
import cn.xpcheng.playandroid.mvp.model.bean.NavigationBean
import cn.xpcheng.playandroid.ui.activity.WebViewActivity
import cn.xpcheng.playandroid.utils.ColorUtils
import cn.xpcheng.playandroid.utils.DisplayUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

/**
 * @author ChengXinPing
 * @time   2019/9/29 16:42
 *
 */
class NavigationAdapter(datas: MutableList<NavigationBean>)
    : BaseQuickAdapter<NavigationBean, BaseViewHolder>(R.layout.item_navigation, datas) {

    override fun convert(helper: BaseViewHolder, item: NavigationBean?) {
        item ?: return
        helper.setText(R.id.tv_item_title, item.name)
        val flowLayout = helper.getView<TagFlowLayout>(R.id.tag_item_name)
        val articles = item.articles
        flowLayout.run {
            adapter = object : TagAdapter<Article>(articles) {
                override fun getView(parent: FlowLayout?, position: Int, t: Article?): View {
                    val tv: TextView = LayoutInflater.from(parent?.context).inflate(R.layout.item_navigation_tag,
                            flowLayout, false) as TextView
                    tv.run {
                        setPadding(DisplayUtil.dp2px(context, 10f), DisplayUtil.dp2px(context, 10f), DisplayUtil.dp2px(context, 10f), DisplayUtil.dp2px(context, 10f))
                        text = Html.fromHtml(t?.title).toString()
                        setTextColor(ColorUtils.randomColor())
                    }

                    setOnTagClickListener { view, position, parent ->
                        Intent(context, WebViewActivity::class.java).run {
                            putExtra(Constant.KEY_WEB_TITLE, articles[position].title)
                            putExtra(Constant.KEY_WEB_URL, articles[position].link)
                            context.startActivity(this)
                        }
                        true
                    }

                    return tv
                }

            }
        }
    }
}