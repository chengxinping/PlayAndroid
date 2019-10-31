package cn.xpcheng.playandroid.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.ProjectListAdapter
import cn.xpcheng.playandroid.base.BaseMVPFragment
import cn.xpcheng.playandroid.constant.Constant
import cn.xpcheng.playandroid.mvp.contract.ProjectListContract
import cn.xpcheng.playandroid.mvp.model.bean.Article
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.presenter.ProjectListPresenter
import cn.xpcheng.playandroid.ui.activity.WebViewActivity
import cn.xpcheng.playandroid.utils.DisplayUtil
import cn.xpcheng.playandroid.widget.itemDecoration.SpaceItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_refresh_recycler.*

/**
 * @author ChengXinPing
 * @time   2019/10/17 14:49
 *
 */
class ProjectListFragment : BaseMVPFragment<ProjectListContract.View, ProjectListPresenter>(), ProjectListContract.View {

    companion object {
        fun getInstance(cid: Int): ProjectListFragment {
            val fragment = ProjectListFragment()
            val args = Bundle()
            args.putInt(Constant.KEY_CONTENT_CID, cid)
            fragment.arguments = args
            return fragment
        }
    }

    private val mProjectList = mutableListOf<Article>()


    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
    }

    //当前cid
    private var cid: Int = -1

    /**
     * is Refresh
     */
    private var isRefresh = true

    private val mAdapter: ProjectListAdapter by lazy {
        ProjectListAdapter(mProjectList)
    }


    override fun createPresenter(): ProjectListPresenter = ProjectListPresenter()

    override fun getLayoutID(): Int = R.layout.fragment_refresh_recycler

    override fun initView() {

        cid = arguments?.getInt(Constant.KEY_CONTENT_CID) ?: -1

        swipeRefreshLayout.run {
            isRefreshing = true
            setOnRefreshListener {
                isRefresh = true
                swipeRefreshLayout.isRefreshing = true
                mAdapter.setEnableLoadMore(false)
                if (cid != -1)
                    mPresenter!!.getProjectList(0, cid)
                else
                    mPresenter!!.getRecentlyProjects(0)
            }
        }

        recycler_view.run {
            layoutManager = mLinearLayoutManager
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(mSpaceItemDecoration)
        }

        mAdapter.run {
            bindToRecyclerView(recycler_view)

            setOnLoadMoreListener({
                isRefresh = false
                swipeRefreshLayout.isRefreshing = false
                val page = mProjectList.size / 15
                if (cid != -1)
                    mPresenter!!.getProjectList(page, cid)
                else
                    mPresenter!!.getRecentlyProjects(page)
            }, recycler_view)

            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                if (mProjectList.size != 0) {
                    val data = mProjectList[position]
                    Intent(activity, WebViewActivity::class.java).run {
                        putExtra(Constant.KEY_WEB_TITLE, data.title)
                        putExtra(Constant.KEY_WEB_URL, data.link)
                        startActivity(this)
                    }
                }
            }
        }
    }

    override fun initData() {
        if (cid != -1)
            mPresenter!!.getProjectList(0, cid)
        else
            mPresenter!!.getRecentlyProjects(0)
    }

    override fun onGetProjectListSuccess(articleBody: ArticleBody) {
        articleBody.datas.let {
            mAdapter.run {
                if (isRefresh) {
                    replaceData(it)
                } else {
                    addData(it)
                }
                val size = it.size
                if (size < articleBody.size)
                    loadMoreEnd(isRefresh)
                else
                    loadMoreComplete()
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
        if (isRefresh) {
            mAdapter.run {
                setEnableLoadMore(true)
            }
        }
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
        mAdapter.run {
            if (isRefresh)
                setEnableLoadMore(true)
            else
                loadMoreFail()
        }
    }


}