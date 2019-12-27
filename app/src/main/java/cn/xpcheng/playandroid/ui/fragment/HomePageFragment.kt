package cn.xpcheng.playandroid.ui.fragment

import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.base.BaseFragment

/**
 * @author ChengXinPing
 * @time   2018/11/1 17:24
 *
 */
class HomePageFragment : BaseFragment() {

    companion object {
        fun getInstance(): HomePageFragment = HomePageFragment()
    }

    override fun getLayoutID(): Int = R.layout.fragment_mine

    override fun initData() {
    }

    override fun initView() {

    }
}