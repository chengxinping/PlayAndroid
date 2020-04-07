package cn.xpcheng.playandroid.ui.activity

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentTransaction
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.base.BaseActivity
import cn.xpcheng.playandroid.ui.fragment.*
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    private var mHomePageFragment: HomePageFragment? = null
    private var mWechatFragment: WechatFragment? = null
    private var mProjectFragment: ProjectFragment? = null
    private var mTreeFragment: KnowledgeTreeFragment? = null
    private var mMineFragment: MineFragment? = null

    private var mIndex: Int = R.id.navigation_home


    override fun getLayoutID(): Int = R.layout.activity_main

    override fun initView() {
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        bottom_navigation.run {
            labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
            setOnNavigationItemSelectedListener { item ->
                switchFragment(item.itemId)
                true
            }
        }
        switchFragment(R.id.navigation_home)

        fab.setOnClickListener {
            when (mIndex) {
                R.id.navigation_home -> {
                    mHomePageFragment?.scrollToTop()
                }
                R.id.navigation_wechat -> {
                    mWechatFragment?.scrollToTop()
                }
                R.id.navigation_project -> {
                    mProjectFragment?.scrollToTop()
                }
                R.id.navigation_tree -> {
                    mTreeFragment?.scrollToTop()

                }
            }
        }
    }

    override fun initData() {
    }

    @SuppressLint("RestrictedApi")
    private fun switchFragment(id: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        mIndex = id
        when (id) {
            R.id.navigation_home -> {
                fab.visibility = View.VISIBLE
                toolbar.title = getString(R.string.title_home)

                if (mHomePageFragment == null) {
                    mHomePageFragment = HomePageFragment.getInstance()
                    transaction.add(R.id.container, mHomePageFragment!!, getString(R.string.title_home))
                } else {
                    transaction.show(mHomePageFragment!!)
                }
            }
            R.id.navigation_wechat -> {
                fab.visibility = View.VISIBLE
                toolbar.title = getString(R.string.title_wechat)
                if (mWechatFragment == null) {
                    mWechatFragment = WechatFragment.getInstance()
                    transaction.add(R.id.container, mWechatFragment!!, getString(R.string.title_wechat))
                } else {
                    transaction.show(mWechatFragment!!)
                }
            }
            R.id.navigation_project -> {
                fab.visibility = View.VISIBLE
                toolbar.title = getString(R.string.title_project)
                if (mProjectFragment == null) {
                    mProjectFragment = ProjectFragment.getInstance()
                    transaction.add(R.id.container, mProjectFragment!!, getString(R.string.title_project))
                } else {
                    transaction.show(mProjectFragment!!)
                }
            }
            R.id.navigation_tree -> {
                fab.visibility = View.VISIBLE
                toolbar.title = getString(R.string.title_tree)
                if (mTreeFragment == null) {
                    mTreeFragment = KnowledgeTreeFragment.getInstance()
                    transaction.add(R.id.container, mTreeFragment!!, getString(R.string.title_tree))
                } else {
                    transaction.show(mTreeFragment!!)
                }
            }
            R.id.navigation_mine -> {
                fab.visibility = View.GONE
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_search -> {

                return true
            }
        }


        return super.onOptionsItemSelected(item)
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
