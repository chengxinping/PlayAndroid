package cn.xpcheng.playandroid.ui.fragment

import android.content.Intent
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.base.BaseFragment
import cn.xpcheng.playandroid.ui.activity.NavigationActivity
import cn.xpcheng.playandroid.ui.activity.SettingActivity
import com.fengchen.uistatus.annotation.UiStatus
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * @author ChengXinPing
 * @time   2018/11/1 17:24
 *
 */
class MineFragment : BaseFragment() {

    companion object {
        fun getInstance(): MineFragment = MineFragment()
    }

    override fun getLayoutID(): Int = R.layout.fragment_mine

    override fun initData() {
    }

    override fun initView() {
        mUiStatusController.changeUiStatusIgnore(UiStatus.CONTENT)
        cl_setting.setOnClickListener {
            Intent(activity!!, SettingActivity::class.java).run {
                startActivity(this)
            }
        }

        cl_navigation.setOnClickListener {
            Intent(activity!!, NavigationActivity::class.java).run {
                startActivity(this)
            }
        }

    }
}