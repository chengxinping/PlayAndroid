package cn.xpcheng.playandroid.ui.fragment

import android.os.Bundle
import android.preference.PreferenceFragment
import cn.xpcheng.playandroid.R


/**
 * @author ChengXinPing
 * @time   2019/9/27 17:21
 *
 */
class SettingFragment : PreferenceFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_setting)
        setHasOptionsMenu(false)
    }
}