package cn.xpcheng.playandroid.adapter

import android.text.Html
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import cn.xpcheng.playandroid.mvp.model.bean.ProjectTreeBean
import cn.xpcheng.playandroid.ui.fragment.KnowledgeListFragment

/**
 * @author ChengXinPing
 * @time   2019/10/17 14:47
 *
 */
class WechatViewPagerAdapter(fm: FragmentManager, val list: List<ProjectTreeBean>) : FragmentStatePagerAdapter(fm) {

    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.clear()
        list.forEach {
            fragments.add(KnowledgeListFragment.getInstance(it.id))
        }
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = list.size

    override fun getPageTitle(position: Int): CharSequence = Html.fromHtml(list[position].name)


    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE

}