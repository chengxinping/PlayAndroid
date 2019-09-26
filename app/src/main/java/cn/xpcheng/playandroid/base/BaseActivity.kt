package cn.xpcheng.playandroid.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem


/**
 * @author ChengXinPing
 * @time   2018/10/31 11:59
 *
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 布局文件id
     */
    @LayoutRes
    protected abstract fun getLayoutID(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        parseIntent()
        initView()
        initData()
    }


    override fun onBackPressed() {
        //Fragment逐个出栈
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    open fun parseIntent() {

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}