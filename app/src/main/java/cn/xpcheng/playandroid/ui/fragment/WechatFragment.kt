package cn.xpcheng.playandroid.ui.fragment

import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * @author ChengXinPing
 * @time   2018/11/1 17:24
 *
 */
class WechatFragment : BaseFragment() {

    companion object {
        fun getInstance(): WechatFragment = WechatFragment()
    }

    override fun getLayoutID(): Int = R.layout.fragment_mine

    override fun initData() {
    }

    override fun initView() {
        text.text = getString(R.string.title_wechat)
    }
}