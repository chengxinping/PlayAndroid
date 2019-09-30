package cn.xpcheng.playandroid.ui.activity

import android.app.Fragment
import android.app.FragmentTransaction
import android.preference.PreferenceActivity.EXTRA_SHOW_FRAGMENT

import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.base.BaseActivity
import cn.xpcheng.playandroid.ui.fragment.SettingFragment
import kotlinx.android.synthetic.main.toolbar.*

/**
 * @author ChengXinPing
 * @time   2019/9/27 17:08
 *
 */
class SettingActivity : BaseActivity() {

    override fun getLayoutID(): Int = R.layout.activity_setting


    override fun initView() {
        val initFragment: String = intent.getStringExtra(EXTRA_SHOW_FRAGMENT) ?: ""


        if (initFragment.isEmpty()) {
            setupFragment(SettingFragment::class.java.name)
        } else {
            setupFragment(initFragment)
        }

        toolbar.run {
            title = getString(R.string.setting)
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun initData() {
    }

    private fun setupFragment(fragmentName: String) {
        val fragment = Fragment.instantiate(this, fragmentName)
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.container, fragment)
        transaction.commitAllowingStateLoss()
    }

}