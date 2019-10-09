package cn.xpcheng.playandroid.ui.activity

import android.view.KeyEvent
import android.webkit.WebView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import cn.xpcheng.playandroid.R
import cn.xpcheng.playandroid.base.BaseActivity
import cn.xpcheng.playandroid.constant.Constant
import com.google.android.material.appbar.AppBarLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.NestedScrollAgentWebView
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * @author ChengXinPing
 * @time   2019/9/26 10:39
 *
 */
class WebViewActivity : BaseActivity() {

    private var mAgentWeb: AgentWeb? = null

    private lateinit var mTitle: String

    private lateinit var mUrl: String

    private val mWebview: NestedScrollAgentWebView by lazy {
        NestedScrollAgentWebView(this)
    }


    override fun getLayoutID(): Int = R.layout.activity_webview


    override fun parseIntent() {
        super.parseIntent()
        intent.extras?.let {
            mUrl = it.getString(Constant.KEY_WEB_URL)
            mTitle = it.getString(Constant.KEY_WEB_TITLE)
        }
    }

    override fun initView() {
        toolbar.apply {
            title = "正在加载中..."
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        initWebView()
    }

    override fun initData() {

    }

    private fun initWebView() {
        val layoutParams = CoordinatorLayout.LayoutParams(-1, -1)
        layoutParams.behavior = AppBarLayout.ScrollingViewBehavior()

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(cl_main, layoutParams)
                .useDefaultIndicator()
                .setWebView(mWebview)
                .setWebChromeClient(mWebViewChromeClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .createAgentWeb()
                .ready()
                .go(mUrl)

    }


    private val mWebViewChromeClient: com.just.agentweb.WebChromeClient? = object : com.just.agentweb.WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            toolbar.title = title
        }
    }

    override fun onBackPressed() {
        mAgentWeb?.let {
            if (!it.back()) {
                super.onBackPressed()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (mAgentWeb?.handleKeyEvent(keyCode, event)!!) {
            true
        } else {
            finish()
            super.onKeyDown(keyCode, event)
        }
    }

//    override fun onResume() {
//        mAgentWeb?.webLifeCycle?.onResume()
//        super.onResume()
//    }

    override fun onPause() {
        mAgentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mAgentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }
}