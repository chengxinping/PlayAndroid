package cn.xpcheng.playandroid.ui.fragment

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.HomeAdapter
import cn.xpcheng.playandroid.base.BaseMVPFragment
import cn.xpcheng.playandroid.constant.Constant
import cn.xpcheng.playandroid.mvp.contract.HomeContract
import cn.xpcheng.playandroid.mvp.model.bean.Article
import cn.xpcheng.playandroid.mvp.model.bean.ArticleBody
import cn.xpcheng.playandroid.mvp.model.bean.Banner
import cn.xpcheng.playandroid.mvp.presenter.HomePresenter
import cn.xpcheng.playandroid.ui.activity.WebViewActivity
import cn.xpcheng.playandroid.utils.DisplayUtil
import cn.xpcheng.playandroid.widget.itemDecoration.SpaceItemDecoration
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.include_banner.view.*

/**
 * @author ChengXinPing
 * @time   2018/11/1 17:24
 *
 */
class HomePageFragment : BaseMVPFragment<HomeContract.View, HomePresenter>(), HomeContract.View {

    companion object {
        fun getInstance(): HomePageFragment = HomePageFragment()
    }


    private val mData = mutableListOf<Article>()

    /**
     * banner view
     */
    private var bannerView: View? = null

    private val mAdapter: HomeAdapter by lazy {
        HomeAdapter(mData)
    }

    private val mLinearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    //RecyclerView分割线
    private val mSpaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration(DisplayUtil.dp2px(activity as Context, 10F))
    }


    override fun createPresenter(): HomePresenter = HomePresenter()

    override fun getLayoutID(): Int = R.layout.fragment_home

    override fun initView() {

        bannerView = layoutInflater.inflate(R.layout.include_banner, null)

        recycler_view.run {
            layoutManager = mLinearLayoutManager
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(mSpaceItemDecoration)
        }

        mAdapter.run {
            addHeaderView(bannerView)
            bindToRecyclerView(recycler_view)
            setOnLoadMoreListener(onRequestLoadMoreListener, recycler_view)


            onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
                if (mData.size != 0) {
                    val data = mData[position]
                    Intent(activity, WebViewActivity::class.java).run {
                        putExtra(Constant.KEY_WEB_TITLE, data.title)
                        putExtra(Constant.KEY_WEB_URL, data.link)
                        startActivity(this)
                    }
                }
            }
        }
        swipeRefreshLayout.run {
            isRefreshing = true
            setOnRefreshListener(onRefreshListener)
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
        val page = mData.size / 20
        mPresenter!!.getHomeArticles(page)
    }

    override fun initData() {
        mPresenter!!.getHomeData()
    }

    //下拉刷新监听
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        isRefresh = true
        swipeRefreshLayout.isRefreshing = true
        mAdapter.setEnableLoadMore(false)
        mPresenter!!.getHomeData()
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


    override fun setBanner(banners: List<Banner>) {

        val imageUrls = ArrayList<String>()
        val bannerTitle = ArrayList<String>()

        banners.forEach {
            imageUrls.add(it.imagePath)
            bannerTitle.add(it.title)

        }


        bannerView?.banner?.run {
            setAutoPlayAble(imageUrls.size > 1)
            setData(imageUrls, bannerTitle)
            setAdapter { _, itemView, model, _ ->
                val options = RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.DATA)
                        .placeholder(R.drawable.ic_placeholder)
                Glide.with(context)
                        .load(model)
                        .transition(DrawableTransitionOptions().crossFade())
                        .apply(options)
                        .into(itemView as ImageView)
            }

            setDelegate { _, _, _, position ->
                val data = banners[position]
                Intent(activity, WebViewActivity::class.java).run {
                    putExtra(Constant.KEY_WEB_TITLE, data.title)
                    putExtra(Constant.KEY_WEB_URL, data.url)
                    startActivity(this)
                }
            }

        }
    }

    override fun setHomeData(articleBody: ArticleBody) {
        articleBody.datas.let {
            mAdapter.run {
                if (isRefresh)
                    replaceData(it)
                else
                    addData(it)
                val size = it.size
                if (size < it.size)
                    loadMoreEnd(isRefresh)
                else
                    loadMoreComplete()
            }
        }

    }
}