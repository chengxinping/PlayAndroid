package cn.xpcheng.playandroid.rx

import cn.xpcheng.playandroid.rx.scheduler.AndroidMainScheduler

/**
 * @author ChengXinPing
 * @time   2019/9/29 11:32
 *
 */
object SchedulerUtils {
    fun <T> ioToAndroidMain(): AndroidMainScheduler<T> {
        return AndroidMainScheduler()
    }
}