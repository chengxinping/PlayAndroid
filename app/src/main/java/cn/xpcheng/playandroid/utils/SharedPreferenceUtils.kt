package cn.xpcheng.playandroid.utils

import android.content.Context
import android.content.SharedPreferences
import cn.xpcheng.playandroid.App
import java.io.*
import kotlin.reflect.KProperty

/**
 * @author ChengXinPing
 * @time   2018/10/31 14:27
 *
 */
class SharedPreferenceUtils<T>(val key: String, private val default: T) {
    companion object {
        private val file_name = "play_android_file"

        private val preferenceUtils: SharedPreferences by lazy {
            App.context.getSharedPreferences(file_name, Context.MODE_PRIVATE)
        }

        /**
         * 清除全部数据
         */
        fun clearPreference() {
            preferenceUtils.edit().clear().apply()
        }

        /**
         * 按照key清除某个sharePreference
         * @param key 键
         */
        fun removePreference(key: String) {
            preferenceUtils.edit().remove(key).apply()
        }

        /**
         * 查询某个key是否已经存在
         * @param key 键
         * @return 是否存在
         */
        fun contains(key: String): Boolean {
            return preferenceUtils.contains(key)
        }
    }

    operator fun getValue(ref: Any?, p: KProperty<*>): T {

        return getSharedPreference(key, default)
    }

    operator fun setValue(ref: Any?, p: KProperty<*>, value: T) {
        putSharedPreference(key, value)
    }

    private fun putSharedPreference(key: String, value: T) = with(preferenceUtils.edit()) {
        when (value) {
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            else -> putString(key, serialize(value))
        }.apply()
    }

    private fun getSharedPreference(key: String, default: T): T = with(preferenceUtils) {
        val res: Any = when (default) {
            is Long -> getLong(key, default)
            is String -> getString(key, default)
            is Int -> getInt(key, default)
            is Boolean -> getBoolean(key, default)
            is Float -> getFloat(key, default)
            else -> deSerialization(getString(key, serialize(default)))
        }
        res as T
    }

    /**
     * 序列化对象

     * @param person
     * *
     * @return
     * *
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun <A> serialize(obj: A): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(
                byteArrayOutputStream)
        objectOutputStream.writeObject(obj)
        var serStr = byteArrayOutputStream.toString("ISO-8859-1")
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8")
        objectOutputStream.close()
        byteArrayOutputStream.close()
        return serStr
    }

    /**
     * 反序列化对象

     * @param str
     * *
     * @return
     * *
     * @throws IOException
     * *
     * @throws ClassNotFoundException
     */
    @Throws(IOException::class, ClassNotFoundException::class)
    private fun <A> deSerialization(str: String): A {
        val redStr = java.net.URLDecoder.decode(str, "UTF-8")
        val byteArrayInputStream = ByteArrayInputStream(
                redStr.toByteArray(charset("ISO-8859-1")))
        val objectInputStream = ObjectInputStream(
                byteArrayInputStream)
        val obj = objectInputStream.readObject() as A
        objectInputStream.close()
        byteArrayInputStream.close()
        return obj
    }

}