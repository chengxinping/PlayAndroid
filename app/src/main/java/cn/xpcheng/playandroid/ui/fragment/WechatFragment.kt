package cn.xpcheng.playandroid.ui.fragment

import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.WechatViewPagerAdapter
import cn.xpcheng.playandroid.base.BaseMVPFragment
import cn.xpcheng.playandroid.mvp.contract.ListContract
import cn.xpcheng.playandroid.mvp.model.bean.ProjectTreeBean
import cn.xpcheng.playandroid.mvp.presenter.WechatPresenter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * @author ChengXinPing
 * @time   2018/11/1 17:24
 *
 */
class WechatFragment : BaseMVPFragment<ListContract.View, WechatPresenter>(), ListContract.View {

    private var mData = mutableListOf<ProjectTreeBean>()

    private val mAdapter: WechatViewPagerAdapter by lazy {
        WechatViewPagerAdapter(childFragmentManager, mData)
    }

    companion object {
        fun getInstance(): WechatFragment = WechatFragment()
    }


    override fun getLayoutID(): Int = R.layout.fragment_project

    override fun initView() {
        tl_project.run {
            setupWithViewPager(vp_project)
            addOnTabSelectedListener(onTabSelectedListener)
        }
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
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

    override fun showLoading() {
        super.showLoading()
        //TODO
    }

    override fun hideLoading() {
        super.hideLoading()
    }

    override fun initData() {
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

    override fun createPresenter(): WechatPresenter = WechatPresenter()

}