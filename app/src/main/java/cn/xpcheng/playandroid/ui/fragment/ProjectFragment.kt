package cn.xpcheng.playandroid.ui.fragment

import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.ProjectViewPagerAdapter
import cn.xpcheng.playandroid.base.BaseMVPFragment
import cn.xpcheng.playandroid.mvp.contract.ListContract
import cn.xpcheng.playandroid.mvp.model.bean.ProjectTreeBean
import cn.xpcheng.playandroid.mvp.presenter.ProjectPresenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * @author ChengXinPing
 * @time   2018/11/1 17:24
 *
 */
class ProjectFragment : BaseMVPFragment<ListContract.View, ProjectPresenter>(), ListContract.View {

    private var mData = mutableListOf<ProjectTreeBean>()

    private val mAdapter: ProjectViewPagerAdapter by lazy {
        ProjectViewPagerAdapter(childFragmentManager, mData)
    }

    companion object {
        fun getInstance(): ProjectFragment = ProjectFragment()
    }

    override fun getLayoutID(): Int = R.layout.fragment_project

    override fun createPresenter(): ProjectPresenter = ProjectPresenter()

    override fun initView() {
        tl_project.run {
            setupWithViewPager(vp_project)
            addOnTabSelectedListener(onTabSelectedListener)
        }
    }


    private val onTabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.let {
                vp_project.setCurrentItem(it.position, false)
            }
        }

    }


    override fun initData() {
        val recentlyProject = ProjectTreeBean(mutableListOf(), -1, -1, "", -1, -1, false, -1)
        mData.add(recentlyProject)
        mPresenter!!.getTree()
    }


    override fun onGetTreeSuccess(list: List<ProjectTreeBean>) {

        list.let {
            mData.addAll(it)
            vp_project.run {
                adapter = mAdapter
                offscreenPageLimit = mData.size
            }
        }
    }

    override fun scrollToTop() {
        if (mAdapter.count == 0) {
            return
        }
        (mAdapter.getItem(vp_project.currentItem) as ProjectListFragment).scrollToTop()
    }
}