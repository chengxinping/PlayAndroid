package cn.xpcheng.playandroid.ui.activity

import android.content.Intent
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.HomeAdapter
import cn.xpcheng.playandroid.base.BaseMVPActivity
import cn.xpcheng.playandroid.constant.Constant
import cn.xpcheng.playandroid.mvp.contract.SearchDetailContract
import cn.xpcheng.playandroid.mvp.model.bean.Article
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.presenter.SearchDetailPresenter
import cn.xpcheng.playandroid.utils.DisplayUtil
import cn.xpcheng.playandroid.widget.itemDecoration.SpaceItemDecoration
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.activity_search_detail.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * @author ChengXinPing
 * @time   2020/4/9 10:14
 *
 */
class SearchDetailActivity : BaseMVPActivity<SearchDetailContract.View, SearchDetailContract.Presenter>(), SearchDetailContract.View {

    private var mKey: String = ""

    private val mDatas = mutableListOf<Article>()

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(this, 10F))
    }

    private val mArticleListAdapt: HomeAdapter by lazy {
        HomeAdapter(mDatas)
    }

    override fun createPresenter(): SearchDetailContract.Presenter = SearchDetailPresenter()

    override fun getLayoutID(): Int = R.layout.activity_search_detail

    override fun parseIntent() {
        super.parseIntent()
        intent.extras?.let {
            mKey = it.getString(Constant.KEY_SEARCH_KEY)
        }
    }

    override fun initView() {

        toolbar.run {
            title = mKey
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
                    Intent(this@SearchDetailActivity, WebViewActivity::class.java).run {
                        putExtra(Constant.KEY_WEB_TITLE, data.title)
                        putExtra(Constant.KEY_WEB_URL, data.link)
                        startActivity(this)
                    }
                }
            }
            setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
                val page = mDatas.size / 20
                mPresenter?.search(mKey, page)
            }, recycler_view)

        }

        fab.setOnClickListener {
            scrollToTop()
        }

    }


    override fun initData() {
        super.initData()
        mPresenter?.search(mKey, 0)
    }


    override fun onSearchSuccess(articleBody: ArticleBody) {
        articleBody.datas?.let {
            mArticleListAdapt.run {
                addData(it)
                val size = it.size
                if (size < articleBody.size)
                    loadMoreEnd(false)
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
}