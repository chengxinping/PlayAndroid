package cn.xpcheng.playandroid.ui.activity

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.base.BaseMVPActivity
import cn.xpcheng.playandroid.constant.Constant
import cn.xpcheng.playandroid.mvp.contract.SearchContract
import cn.xpcheng.playandroid.mvp.model.bean.HotKey
import cn.xpcheng.playandroid.mvp.model.bean.SearchHistory
import cn.xpcheng.playandroid.mvp.presenter.SearchPresenter
import cn.xpcheng.playandroid.utils.ColorUtils
import cn.xpcheng.playandroid.utils.DisplayUtil
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar.*


/**
 * @author ChengXinPing
 * @time   2020/4/7 16:11
 *
 */
class SearchActivity : BaseMVPActivity<SearchContract.View, SearchContract.Presenter>(), SearchContract.View {
    override fun createPresenter(): SearchContract.Presenter = SearchPresenter()

    override fun getLayoutID(): Int = R.layout.activity_search


    private val mHotKeys = mutableListOf<HotKey>()

    private val mHistoryKeys = mutableListOf<SearchHistory>()

    override fun initView() {
        toolbar.run {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        iv_delete_history.setOnClickListener {
            mPresenter?.deleteHistory()
        }

        hot_flow_layout.setOnTagClickListener { _, position, _ ->
            if (mHotKeys.isNotEmpty()) {
                goToSearchDetailActivity(mHotKeys[position].name)
                true
            } else {
                false
            }
        }

        history_flow_layout.setOnTagClickListener { _, position, _ ->
            if (mHistoryKeys.isNotEmpty()) {
                goToSearchDetailActivity(mHistoryKeys[position].key)
                true
            } else {
                false
            }
        }
    }

    override fun initData() {
        super.initData()
        mPresenter?.getHotKeys()
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.getHistoryKeys()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.run {
            maxWidth = Integer.MAX_VALUE
            onActionViewExpanded()
            queryHint = "发现更多内容"
            isSubmitButtonEnabled = true
            try {
                val field = searchView.javaClass.getDeclaredField("mGoButton")
                field.isAccessible = true
                val mGoButton = field.get(searchView) as ImageView
                mGoButton.setImageResource(R.drawable.ic_search_24dp)
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        goToSearchDetailActivity(query.toString())
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun goToSearchDetailActivity(key: String) {
        mPresenter?.saveSearchKey(key)

        //Intent
        Intent(this, SearchDetailActivity::class.java).run {
            putExtra(Constant.KEY_SEARCH_KEY, key)
            startActivity(this)
        }
    }

    override fun onGetHotKeysSuccess(hotKeys: MutableList<HotKey>) {
        mHotKeys.addAll(hotKeys)
        if (hotKeys.isNotEmpty()) {
            tv_hot_search.visibility = View.VISIBLE
            hot_flow_layout.visibility = View.VISIBLE

            hotKeys.run {
                hot_flow_layout.adapter = object : TagAdapter<HotKey>(this) {
                    override fun getView(parent: FlowLayout?, position: Int, t: HotKey?): View {
                        val tv: TextView = LayoutInflater.from(parent?.context).inflate(R.layout.item_navigation_tag,
                                hot_flow_layout, false) as TextView
                        tv.run {
                            setPadding(DisplayUtil.dp2px(context, 10f), DisplayUtil.dp2px(context, 10f), DisplayUtil.dp2px(context, 10f), DisplayUtil.dp2px(context, 10f))
                            text = Html.fromHtml(t?.name).toString()
                            setTextColor(ColorUtils.randomColor())
                        }
                        return tv
                    }
                }
            }
        }
    }

    override fun onGetHistoryKeysSuccess(keys: MutableList<SearchHistory>) {
        mHistoryKeys.addAll(keys)
        if (keys.isNotEmpty()) {
            layout_history.visibility = View.VISIBLE
            history_flow_layout.visibility = View.VISIBLE

            keys.run {
                history_flow_layout.adapter = object : TagAdapter<SearchHistory>(this) {
                    override fun getView(parent: FlowLayout?, position: Int, t: SearchHistory?): View {
                        val tv: TextView = LayoutInflater.from(parent?.context).inflate(R.layout.item_navigation_tag,
                                hot_flow_layout, false) as TextView
                        tv.run {
                            setPadding(DisplayUtil.dp2px(context, 10f), DisplayUtil.dp2px(context, 10f), DisplayUtil.dp2px(context, 10f), DisplayUtil.dp2px(context, 10f))
                            text = Html.fromHtml(t?.key).toString()
                            setTextColor(ContextCompat.getColor(this@SearchActivity, R.color.grey_search))
                        }
                        return tv
                    }
                }
            }
        }
    }

    override fun onDeleteHistorySuccess() {
        layout_history.visibility = View.GONE
        history_flow_layout.visibility = View.GONE
    }

}