package cn.xpcheng.playandroid

import cn.xpcheng.playandroid.base.BaseMVPActivity
import cn.xpcheng.playandroid.mvp.contract.HomePageContract
import cn.xpcheng.playandroid.mvp.model.Banner
import cn.xpcheng.playandroid.mvp.presenter.HomePagePresenter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : BaseMVPActivity<HomePageContract.View, HomePagePresenter>(), HomePageContract.View {


    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        text.run {
            text = "测试banner"
            onClick { mPresenter?.getBanner() }
        }

    }


    override fun initData() {
        super.initData()
        mPresenter?.getBanner()
    }

    override fun showLoading() {

    }

    override fun createPresenter(): HomePagePresenter = HomePagePresenter()

    override fun hideLoading() {
    }

    override fun showError(errorMsg: String) {
        Logger.e(errorMsg)
    }

    override fun onGetBannerSuccess(banners: List<Banner>) {
        text.run {
            text = banners[0].title
        }
    }

}
