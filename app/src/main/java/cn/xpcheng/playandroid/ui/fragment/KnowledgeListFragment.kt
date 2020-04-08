package cn.xpcheng.playandroid.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.ArticleListAdapter
import cn.xpcheng.playandroid.base.BaseMVPFragment
import cn.xpcheng.playandroid.constant.Constant
import cn.xpcheng.playandroid.mvp.contract.KnowledgeListContract
import cn.xpcheng.playandroid.mvp.model.bean.Article
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.presenter.KnowledgeListPresenter
import cn.xpcheng.playandroid.ui.activity.WebViewActivity
import cn.xpcheng.playandroid.utils.DisplayUtil
import cn.xpcheng.playandroid.widget.itemDecoration.SpaceItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_refresh_recycler.*

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

    private val mDatas = mutableListOf<Article>()

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
        ArticleListAdapter(activity, mDatas)
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
            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                if (mDatas.size != 0) {
                    val data = mDatas[position]
                    Intent(activity, WebViewActivity::class.java).run {
                        putExtra(Constant.KEY_WEB_TITLE, data.title)
                        putExtra(Constant.KEY_WEB_URL, data.link)
                        startActivity(this)
                    }
                }
            }
            setOnLoadMoreListener(onRequestLoadMoreListener, recycler_view)
        }
    }

    /**
     * is Refresh
     */
    private var isRefresh = true

    /**
     * LoadMoreListener
     */
    private val onRequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        isRefresh = false
        swipeRefreshLayout.isRefreshing = false
        val page = mDatas.size / 20
        mPresenter!!.getKnowledgeList(page, cid)
    }

    override fun initData() {
        mPresenter!!.getKnowledgeList(0, cid)
    }

    override fun onGetKnowledgeListDone(articles: ArticleBody) {
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
        articles.datas.let {
            mArticleListAdapt.run {
                if (isRefresh)
                    replaceData(it)
                else
                    addData(it)
                val size = it.size
                if (size < articles.size)
                    loadMoreEnd(isRefresh)
                else
                    loadMoreComplete()
            }
        }
    }

    override fun scrollToTop() {
        recycler_view.run {
            if (mLinearLayoutManager.findFirstVisibleItemPosition() > 20) {
                scrollToPosition(0)
            } else {
                smoothScrollToPosition(0)
            }
        }
    }

    //下拉刷新监听
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        isRefresh = true
        swipeRefreshLayout.isRefreshing = true
        mArticleListAdapt.setEnableLoadMore(false)
        mPresenter!!.getKnowledgeList(0, cid)
    }


    override fun hideLoading() {
        super.hideLoading()
        if (isRefresh) {
            mArticleListAdapt.run {
                setEnableLoadMore(true)
            }
        }
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        mArticleListAdapt.run {
            if (isRefresh)
                setEnableLoadMore(true)
            else
                loadMoreFail()
        }
    }
}