package cn.xpcheng.playandroid.ui.activity

import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.adapter.KnowledgeListViewPagerAdapter
import cn.xpcheng.playandroid.base.BaseActivity
import cn.xpcheng.playandroid.constant.Constant
import cn.xpcheng.playandroid.mvp.model.bean.Knowledge
import cn.xpcheng.playandroid.mvp.model.bean.KnowledgeTree
import cn.xpcheng.playandroid.ui.fragment.KnowledgeListFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_knowledge_list.*

/**
 * @author ChengXinPing
 * @time   2018/11/21 9:53
 *
 */
class KnowledgeListActivity : BaseActivity() {

    //数据
    private var knowledges = mutableListOf<Knowledge>()

    private val mViewPagerAdapter: KnowledgeListViewPagerAdapter by lazy {
        KnowledgeListViewPagerAdapter(supportFragmentManager, knowledges)
    }

    override fun getLayoutID(): Int = R.layout.activity_knowledge_list

    override fun parseIntent() {
        super.parseIntent()
        intent.extras?.let { it ->
            it.getSerializable(Constant.KEY_KNOWLEDGE_LIST_DATA)?.let {
                (it as KnowledgeTree).children.let { children ->
                    knowledges.addAll(children)
                }
            }
        }
    }

    override fun initView() {
        toolbar.run {
            title = (intent.extras.getSerializable(Constant.KEY_KNOWLEDGE_LIST_DATA) as KnowledgeTree).name
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        viewPager_knowledge.run {
            adapter = mViewPagerAdapter
            offscreenPageLimit = knowledges.size
        }

        tl_knowledge_list.run {
            setupWithViewPager(viewPager_knowledge)
            addOnTabSelectedListener(onTabSelectedListener)
        }

        fab.setOnClickListener {
            if (mViewPagerAdapter.count == 0) {
                return@setOnClickListener
            }

            (mViewPagerAdapter.getItem(viewPager_knowledge.currentItem) as KnowledgeListFragment).scrollToTop()
        }
    }

    override fun initData() {

    }

    private val onTabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.let {
                viewPager_knowledge.setCurrentItem(it.position, false)
            }
        }

    }

}