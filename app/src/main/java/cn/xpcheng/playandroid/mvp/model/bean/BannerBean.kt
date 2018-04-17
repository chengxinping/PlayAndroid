package cn.xpcheng.playandroid.mvp.model.bean

/**
 * @author ChengXinPing
 * @time   2018/4/17 21:12
 *
 */

import java.io.Serializable


data class BannerBean(val desc: String,
                      val id: Int,
                      val imagePath: String,
                      val isVisible: Int,
                      val order: Int,
                      val title: String,
                      val type: Int,
                      val url: String) : Serializable
