package cn.xpcheng.playandroid.ui.activity

import android.view.MotionEvent
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.NavigationAdapter
import cn.xpcheng.playandroid.adapter.NavigationTagAdapter
import cn.xpcheng.playandroid.base.BaseMVPActivity
import cn.xpcheng.playandroid.ext.toast
import cn.xpcheng.playandroid.mvp.contract.NavigationContract
import cn.xpcheng.playandroid.mvp.model.bean.NavigationBean
import cn.xpcheng.playandroid.mvp.presenter.NavigationPresenter
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.toolbar.*
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.widget.TabView

/**
 * @author ChengXinPing
 * @time   2019/9/29 10:44
 *
 */
class NavigationActivity : BaseMVPActivity<NavigationContract.View, NavigationContract.Presenter>(), NavigationContract.View {

    private var datas = mutableListOf<NavigationBean>()

    private val mAdapter: NavigationAdapter by lazy {
        NavigationAdapter(datas)
    }

    private var mTabCurrentPosition: Int = 0

    private var isRecyclerScroll = false


    override fun getLayoutID(): Int = R.layout.activity_navigation


    override fun initView() {
        toolbar.run {
            title = getString(R.string.navigation)
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        recycler_navigation.run {
            layoutManager = LinearLayoutManager(this@NavigationActivity)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = mAdapter
        }

        mAdapter.run {
            bindToRecyclerView(recycler_navigation)
        }

        vertical_tab_layout_navigation.run {
            addOnTabSelectedListener(object : VerticalTabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabView?, position: Int) {
                }

                override fun onTabSelected(tab: TabView?, position: Int) {
                    isRecyclerScroll = false
                    recycler_navigation.smoothScrollToPosition(position)
                    mTabCurrentPosition = position
                }

            })
        }

        recycler_navigation.run {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    //当不滚动时
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //获取最第一个完全显示的item
                        val lastVisibleItem: Int = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        if (lastVisibleItem != mTabCurrentPosition && isRecyclerScroll) {
                            vertical_tab_layout_navigation.setTabSelected(lastVisibleItem)
                        }
                    }
                }
            })

            setOnTouchListener { _, event ->
                //当滑动是有recyclerView触发事 侧边栏才修改recyclervire位置
                if (event.action == MotionEvent.ACTION_DOWN) {
                    isRecyclerScroll = true
                }
                false
            }
        }
    }

    override fun initData() {
        super.initData()
        mPresenter?.getNavigationList()
    }

    override fun createPresenter(): NavigationContract.Presenter = NavigationPresenter()

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showError(errorMsg: String) {
        errorMsg.toast(this)
    }

    override fun onGetNavigationListSuccess(list: List<NavigationBean>) {
        list.let {
            vertical_tab_layout_navigation.run {
                setTabAdapter(NavigationTagAdapter(this@NavigationActivity, list))
            }

            mAdapter.run {
                replaceData(it)
                setEnableLoadMore(false)
            }
        }
    }
}