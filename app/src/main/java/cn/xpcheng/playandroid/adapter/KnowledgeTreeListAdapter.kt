package cn.xpcheng.playandroid.adapter

import android.content.Context
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.mvp.model.KnowledgeTree
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author ChengXinPing
 * @time   2018/11/7 17:42
 *
 */
class KnowledgeTreeListAdapter(private val context: Context?, datas: MutableList<KnowledgeTree>) : BaseQuickAdapter<KnowledgeTree, BaseViewHolder>(R.layout.item_knowledge_tree, datas) {

    override fun convert(helper: BaseViewHolder?, item: KnowledgeTree?) {
        helper?.setText(R.id.item_tv_knowledge_tree_title, item?.name)
        //let操作费 表示非空时
        item?.children.let {
            helper?.setText(R.id.item_tv_knowledge_tree_des,
                    it?.joinToString("   ", transform = { child ->
                        child.name
                    }))
        }
    }
}