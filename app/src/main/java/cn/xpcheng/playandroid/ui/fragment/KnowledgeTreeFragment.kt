package cn.xpcheng.playandroid.ui.fragment

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.KnowledgeTreeListAdapter
import cn.xpcheng.playandroid.base.BaseMVPFragment
import cn.xpcheng.playandroid.mvp.contract.KnowledgeTreeContract
import cn.xpcheng.playandroid.mvp.model.KnowledgeTree
import cn.xpcheng.playandroid.mvp.presenter.KnowledgeTreePresenter
import cn.xpcheng.playandroid.utils.DisplayUtil
import cn.xpcheng.playandroid.widget.itemDecoration.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_konwledge_tree.*
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

    override fun getLayoutID(): Int = R.layout.fragment_konwledge_tree

    override fun createPresenter(): KnowledgeTreePresenter = KnowledgeTreePresenter()

    /**
     * datas
     */
    private val mDatas = mutableListOf<KnowledgeTree>()

    /**
     * Adapter
     */
    private val mKnowledgeTreeListAdapter: KnowledgeTreeListAdapter by lazy {
        KnowledgeTreeListAdapter(activity as Context?, mDatas)
    }

    /**
     * LinearLayoutManager
     */
    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

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
        rv_knowledge_tree.run {
            layoutManager = mLinearLayoutManager
            adapter = mKnowledgeTreeListAdapter
            itemAnimator = DefaultItemAnimator()
            mSpaceItemDecoration?.let {
                addItemDecoration(it)
            }

        }

        mKnowledgeTreeListAdapter.run {
            bindToRecyclerView(rv_knowledge_tree)
            setEnableLoadMore(false)
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

    /**
     * RefreshListener
     */
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        mPresenter!!.getKnowledgeTree()
    }

}
