package cn.xpcheng.playandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import cn.xpcheng.playandroid.mvp.contract.BannerContract
import cn.xpcheng.playandroid.mvp.model.bean.BannerBean
import cn.xpcheng.playandroid.mvp.presenter.BannerPresenter
import com.orhanobut.logger.Logger

class MainActivity : AppCompatActivity(), BannerContract.View {

    private var mPresenter: BannerPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter = BannerPresenter()
        mPresenter?.attachView(this)
        mPresenter?.getBannerData()
    }

    override fun showBanner(bannerBeans: List<BannerBean>) {
        Logger.d(bannerBeans)
        Toast.makeText(this, bannerBeans[0].desc, Toast.LENGTH_SHORT).show()
    }

    override fun showError(errorMsg: String, errorCode: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }
}
