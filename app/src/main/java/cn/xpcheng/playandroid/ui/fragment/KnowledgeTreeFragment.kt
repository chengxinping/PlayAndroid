package cn.xpcheng.playandroid.ui.fragment

import android.content.Context
import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.KnowledgeTreeListAdapter
import cn.xpcheng.playandroid.base.BaseMVPFragment
import cn.xpcheng.playandroid.constant.Constant
import cn.xpcheng.playandroid.mvp.contract.KnowledgeTreeContract
import cn.xpcheng.playandroid.mvp.model.bean.KnowledgeTree
import cn.xpcheng.playandroid.mvp.presenter.KnowledgeTreePresenter
import cn.xpcheng.playandroid.ui.activity.KnowledgeListActivity
import cn.xpcheng.playandroid.utils.DisplayUtil
import cn.xpcheng.playandroid.widget.itemDecoration.SpaceItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_refresh_recycler.*
import org.jetbrains.anko.support.v4.toast

/**
 * @author ChengXinPing
 * @time   2018/11/1 17:24
 *
 */
class KnowledgeTreeFragment : BaseMVPFragment<KnowledgeTreeContract.View, KnowledgeTreePresenter>(), KnowledgeTreeContract.View {

    companion object {
        fun getInstance(): KnowledgeTreeFragment = KnowledgeTreeFragment()
    }

    override fun getLayoutID(): Int = R.layout.fragment_refresh_recycler

    override fun createPresenter(): KnowledgeTreePresenter = KnowledgeTreePresenter()

    //数据
    private val data = mutableListOf<KnowledgeTree>()

    private val mKnowledgeTreeListAdapter: KnowledgeTreeListAdapter by lazy {
        KnowledgeTreeListAdapter(activity, data)
    }


    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        activity.let {
            SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
        }
    }

    override fun initView() {

        swipeRefreshLayout.run {
            isRefreshing = true
            setOnRefreshListener(onRefreshListener)
        }
        recycler_view.run {
            layoutManager = mLinearLayoutManager
            adapter = mKnowledgeTreeListAdapter
            itemAnimator = DefaultItemAnimator()
            mSpaceItemDecoration?.let {
                addItemDecoration(it)
            }

        }

        mKnowledgeTreeListAdapter.run {
            bindToRecyclerView(recycler_view)
            setEnableLoadMore(false)
            onItemClickListener = this@KnowledgeTreeFragment.onItemClickListener
        }
    }

    override fun initData() {
        mPresenter!!.getKnowledgeTree()
    }

    override fun onGetKnowledgeTreeSuccess(knowledgeTree: List<KnowledgeTree>) {
        knowledgeTree.let {
            mKnowledgeTreeListAdapter.run {
                replaceData(knowledgeTree)
            }
        }
    }

    override fun showLoading() {
        super.showLoading()
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        super.hideLoading()
        swipeRefreshLayout.isRefreshing = false
        mKnowledgeTreeListAdapter.run {
            loadMoreComplete()
        }
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        mKnowledgeTreeListAdapter.run {
            loadMoreFail()
        }
        toast(errorMsg)
    }

    //下拉刷新监听
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {

        mPresenter!!.getKnowledgeTree()
    }


    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
        if (data.size != 0) {
            Intent(activity, KnowledgeListActivity::class.java).run {
                putExtra(Constant.KEY_KNOWLEDGE_LIST_DATA, this@KnowledgeTreeFragment.data[position])
                startActivity(this)
            }
        }
    }
}
