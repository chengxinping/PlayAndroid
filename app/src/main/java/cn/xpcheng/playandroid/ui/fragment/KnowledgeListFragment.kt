package cn.xpcheng.playandroid.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.ArticleListAdapter
import cn.xpcheng.playandroid.base.BaseMVPFragment
import cn.xpcheng.playandroid.constant.Constant
import cn.xpcheng.playandroid.mvp.contract.KnowledgeListContract
import cn.xpcheng.playandroid.mvp.model.bean.Article
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.presenter.KnowledgeListPresenter
import cn.xpcheng.playandroid.utils.DisplayUtil
import cn.xpcheng.playandroid.widget.itemDecoration.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_refresh_recycler.*
import org.jetbrains.anko.support.v4.toast

/**
 * @author ChengXinPing
 * @time   2018/11/28 14:45
 *
 */
class KnowledgeListFragment : BaseMVPFragment<KnowledgeListContract.View, KnowledgeListPresenter>(), KnowledgeListContract.View {

    companion object {
        fun getInstance(cid: Int): KnowledgeListFragment {
            val fragment = KnowledgeListFragment()
            val args = Bundle()
            args.putInt(Constant.KEY_CONTENT_CID, cid)
            fragment.arguments = args
            return fragment
        }
    }

    //当前cid
    private var cid: Int = 0

    private val data = mutableListOf<Article>()

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        activity.let {
            SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
        }
    }

    private val mArticleListAdapt: ArticleListAdapter by lazy {
        ArticleListAdapter(activity, data)
    }

    override fun createPresenter(): KnowledgeListPresenter = KnowledgeListPresenter()

    override fun getLayoutID(): Int = R.layout.fragment_refresh_recycler


    override fun initView() {
        cid = arguments?.getInt(Constant.KEY_CONTENT_CID) ?: 0
        swipeRefreshLayout.run {
            isRefreshing = true
            setOnRefreshListener(onRefreshListener)
        }

        recycler_view.run {
            layoutManager = mLinearLayoutManager
            adapter = mArticleListAdapt
            itemAnimator = DefaultItemAnimator()
            mSpaceItemDecoration?.let {
                addItemDecoration(it)
            }
        }

        mArticleListAdapt.run {
            bindToRecyclerView(recycler_view)
        }
    }

    override fun initData() {
        mPresenter!!.getKnowledgeList(0, cid)
    }

    override fun onGetKonwledgeListDone(articles: ArticleBody) {
        articles.datas.let {
            mArticleListAdapt.run {
                replaceData(it)
            }
        }
    }

    //下拉刷新监听
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        swipeRefreshLayout.isRefreshing = true
        mArticleListAdapt.setEnableLoadMore(false)
        mPresenter!!.getKnowledgeList(0, cid)
    }

    override fun showLoading() {
        super.showLoading()
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        super.hideLoading()
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        toast(errorMsg)
    }
}