package cn.xpcheng.playandroid.ui.fragment

import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_wechat.*

/**
 * @author ChengXinPing
 * @time   2018/11/1 17:24
 *
 */
class ProjectFragment : BaseFragment() {

    companion object {
        fun getInstance(): ProjectFragment = ProjectFragment()
    }

    override fun getLayoutID(): Int = R.layout.fragment_wechat

    override fun initData() {
    }

    override fun initView() {
        text.text = getString(R.string.title_project)
    }
}