package cn.xpcheng.playandroid.ui.activity

import android.support.v4.app.FragmentTransaction
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.base.BaseActivity
import cn.xpcheng.playandroid.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    private var mHomePageFragment: HomePageFragment? = null
    private var mWechatFragment: WechatFragment? = null
    private var mProjectFragment: ProjectFragment? = null
    private var mTreeFragment: KnowledgeTreeFragment? = null
    private var mMineFragment: MineFragment? = null


    override fun getLayoutID(): Int = R.layout.activity_main

    override fun initView() {
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        bottom_navigation.run {
            labelVisibilityMode = 1
            setOnNavigationItemSelectedListener { item ->
                switchFragment(item.itemId)
                true
            }
        }
        switchFragment(R.id.navigation_home)
    }

    override fun initData() {
    }

    private fun switchFragment(id: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (id) {
            R.id.navigation_home -> {
                toolbar.title = getString(R.string.title_home)

                if (mHomePageFragment == null) {
                    mHomePageFragment = HomePageFragment.getInstance()
                    transaction.add(R.id.container, mHomePageFragment!!, getString(R.string.title_home))
                } else {
                    transaction.show(mHomePageFragment!!)
                }
            }
            R.id.navigation_wechat -> {
                toolbar.title = getString(R.string.title_wechat)
                if (mWechatFragment == null) {
                    mWechatFragment = WechatFragment.getInstance()
                    transaction.add(R.id.container, mWechatFragment!!, getString(R.string.title_wechat))
                } else {
                    transaction.show(mWechatFragment!!)
                }
            }
            R.id.navigation_project -> {
                toolbar.title = getString(R.string.title_project)
                if (mProjectFragment == null) {
                    mProjectFragment = ProjectFragment.getInstance()
                    transaction.add(R.id.container, mProjectFragment!!, getString(R.string.title_project))
                } else {
                    transaction.show(mProjectFragment!!)
                }
            }
            R.id.navigation_tree -> {
                toolbar.title = getString(R.string.title_tree)
                if (mTreeFragment == null) {
                    mTreeFragment = KnowledgeTreeFragment.getInstance()
                    transaction.add(R.id.container, mTreeFragment!!, getString(R.string.title_tree))
                } else {
                    transaction.show(mTreeFragment!!)
                }
            }
            R.id.navigation_mine -> {
                toolbar.title = getString(R.string.title_mine)
                if (mMineFragment == null) {
                    mMineFragment = MineFragment.getInstance()
                    transaction.add(R.id.container, mMineFragment!!, getString(R.string.title_mine))
                } else {
                    transaction.show(mMineFragment!!)
                }
            }
        }
        transaction.commit()
    }


    /**
     * 隐藏所有的Fragment
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomePageFragment?.let { transaction.hide(it) }
        mWechatFragment?.let { transaction.hide(it) }
        mProjectFragment?.let { transaction.hide(it) }
        mTreeFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }
}
