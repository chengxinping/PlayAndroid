package cn.xpcheng.playandroid.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import cn.xpcheng.playandroid.mvp.model.bean.Knowledge
import cn.xpcheng.playandroid.ui.fragment.KnowledgeListFragment

/**
 * @author ChengXinPing
 * @time   2018/11/28 14:35
 *
 */
class KnowledgeListViewPagerAdapter(fm: FragmentManager, val list: List<Knowledge>) : FragmentStatePagerAdapter(fm) {

    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.clear()
        list.forEach {
            fragments.add(KnowledgeListFragment.getInstance(it.id))
        }
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = list.size

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].name
    }

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE
}