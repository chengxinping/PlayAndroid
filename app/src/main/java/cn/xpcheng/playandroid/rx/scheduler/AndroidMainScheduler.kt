package cn.xpcheng.playandroid.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author ChengXinPing
 * @time   2019/9/29 11:33
 *
 */
class AndroidMainScheduler<T> : BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())